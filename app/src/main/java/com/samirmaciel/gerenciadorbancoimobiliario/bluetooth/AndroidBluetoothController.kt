package com.sm.gamecolor.bluetooth

import android.Manifest
import android.annotation.SuppressLint
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.BluetoothServerSocket
import android.bluetooth.BluetoothSocket
import android.content.Context
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.util.Log
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.update
import java.io.IOException
import java.util.UUID

class AndroidBluetoothController(val context: Context) : BluetoothController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private var dataTransferService: BluetoothDataTransferService? = null

    private val _isConnected = MutableStateFlow(false)
    private val _scannedDevices = MutableStateFlow<List<BluetoothDevice>>(emptyList())
    override val isConnected: StateFlow<Boolean>
        get() = _isConnected.asStateFlow()

    override val scannedDevices: StateFlow<List<BluetoothDevice>>
        get() = _scannedDevices.asStateFlow()

    private val _errors = MutableSharedFlow<String>()
    override val errors: SharedFlow<String>
        get() = _errors.asSharedFlow()

    @SuppressLint("MissingPermission")
    private val foundDeviceReceiver = FoundDeviceReceiver { device ->
        _scannedDevices.update { devices ->
            val newDevice = if (device.bluetoothClass.deviceClass == 524) device else null

            if (newDevice !in devices && newDevice != null) {
                devices + newDevice
            } else {
                devices
            }
        }
    }

    @SuppressLint("MissingPermission")
    private val bluetoothStateReceiver = BluetoothStateReceiver { isConnected, bluetoothDevice ->


    }

    private var currentServerSocket: BluetoothServerSocket? = null
    private var currentClientSocket: BluetoothSocket? = null

    init {
        context.registerReceiver(
            bluetoothStateReceiver,
            IntentFilter().apply {
                addAction(BluetoothAdapter.ACTION_CONNECTION_STATE_CHANGED)
                addAction(BluetoothDevice.ACTION_ACL_CONNECTED)
                addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED)
            }
        )
    }

    @SuppressLint("MissingPermission")
    override fun startDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) {
            Log.e("DeviceTESTE", "startDiscovery: Don't have permission to bluetooth scan! ")
            return
        }

        context.registerReceiver(
            foundDeviceReceiver,
            IntentFilter(BluetoothDevice.ACTION_FOUND)
        )

        _scannedDevices.value = mutableListOf()

        updatePairedDevices()

        Log.i("DeviceTESTE", "startDiscovering... ")
        bluetoothAdapter?.startDiscovery()
    }

    @SuppressLint("MissingPermission")
    override fun stopDiscovery() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_SCAN)) {
            return
        }

        bluetoothAdapter?.cancelDiscovery()
    }

    @SuppressLint("MissingPermission")
    override fun startBluetoothServer(): Flow<TransferConnectionResult> {
        return flow {
            if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
                Log.e("DeviceTESTE", "startBluetoothServer error: No BLUETOOTH_CONNECT permission")
                throw SecurityException("No BLUETOOTH_CONNECT permission")
            }

            currentServerSocket = bluetoothAdapter?.listenUsingRfcommWithServiceRecord(
                "bank_service",
                UUID.fromString(SERVICE_UUID)
            )

            var shouldLoop = true
            while (shouldLoop) {
                Log.i("DeviceTESTE", "BluetoothServer start listen...")
                currentClientSocket = try {
                    currentServerSocket?.accept()
                } catch (e: IOException) {
                    Log.e("DeviceTESTE", "startBluetoothServer error: ${e.message}")
                    shouldLoop = false
                    null
                }

                currentClientSocket?.let {
                    val service = BluetoothDataTransferService(it)
                    dataTransferService = service

                    emitAll(
                        service
                            .listenForIncomingMessages()
                            .map {
                                TransferConnectionResult.TransferSucceeded(it)
                            }
                    )
                }
            }
        }.onCompletion {
            //closeConnection()
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("MissingPermission")
    override fun connectToDevice(device: BluetoothDevice, player: Player?): Flow<TransferConnectionResult> {
        return flow {
            if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
                throw SecurityException("No BLUETOOTH_CONNECT permission")
            }

            currentClientSocket = bluetoothAdapter
                ?.getRemoteDevice(device.address)
                ?.createRfcommSocketToServiceRecord(
                    UUID.fromString(SERVICE_UUID)
                )
            stopDiscovery()

            currentClientSocket?.let { socket ->
                try {
                    socket.connect()

                    emit(TransferConnectionResult.ConnectionEstablished)

                    player?.let {
                        sendMessage(player)
                    }


                    BluetoothDataTransferService(socket).also {
                        dataTransferService = it
                        emitAll(
                            it.listenForIncomingMessages()
                                .map { TransferConnectionResult.TransferSucceeded(it) }
                        )
                    }
                } catch (e: IOException) {

                    socket.close()
                    currentClientSocket = null
                    emit(TransferConnectionResult.Error("Connection was interrupted"))
                }
            }
        }.onCompletion {
            closeConnection()
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("MissingPermission")
    override suspend fun sendMessage(message: Any): Any? {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return null
        }

        if (dataTransferService == null) {
            return null
        }

        dataTransferService?.sendMessage(message)

        return message
    }

    override fun closeConnection() {
        currentClientSocket?.close()
        currentServerSocket?.close()
        currentClientSocket = null
        currentServerSocket = null
    }

    override fun release() {
        context.unregisterReceiver(foundDeviceReceiver)
        context.unregisterReceiver(bluetoothStateReceiver)
        closeConnection()
    }

    @SuppressLint("MissingPermission")
    fun updatePairedDevices() {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            Log.e(
                "DeviceTESTE",
                "updatePairedDevices: Don't have permission to bluetooth scan paried devices ",
            )
            return
        }

        bluetoothAdapter
            ?.bondedDevices
            ?.mapNotNull {
                it
            }
            ?.also { devices ->
                _scannedDevices.update { sourceUserGameList ->
                    val newUserGameList = sourceUserGameList.toMutableList()
                    for (device in devices) {
                        if (device in sourceUserGameList) {
                            continue
                        } else {
                            if(device.bluetoothClass.deviceClass == 524){
                                newUserGameList.add(device)
                            }
                        }
                    }
                    newUserGameList
                }

            }
    }

    private fun hasPermission(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val SERVICE_UUID = "27b7d1da-08c7-4505-a6d1-2459987e5e2d"
    }
}