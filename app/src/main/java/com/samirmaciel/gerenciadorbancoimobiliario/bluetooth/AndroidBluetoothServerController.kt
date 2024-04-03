package com.samirmaciel.gerenciadorbancoimobiliario.bluetooth

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
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue
import com.sm.gamecolor.bluetooth.BluetoothDataTransferService
import com.sm.gamecolor.bluetooth.BluetoothStateReceiver
import com.sm.gamecolor.bluetooth.TransferConnectionResult
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

class AndroidBluetoothServerController(private val context: Context) : BluetoothServerController {

    private val bluetoothManager by lazy {
        context.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private var _devicesConnected = MutableStateFlow<MutableMap<String, BluetoothDataTransferService>>(
        mutableMapOf()
    )

   override val devicesConnected: StateFlow<MutableMap<String, BluetoothDataTransferService>>
        get() = _devicesConnected.asStateFlow()

    private val _errors = MutableSharedFlow<String>()
    override val errors: SharedFlow<String>
        get() = _errors.asSharedFlow()

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
    override fun startBluetoothServer(): Flow<ServerConnectionResult> {
        return flow {
            if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
                throw SecurityException("No BLUETOOTH_CONNECT permission")
            }

            currentServerSocket = bluetoothAdapter?.listenUsingRfcommWithServiceRecord(
                "bank_service",
                UUID.fromString(SERVICE_UUID)
            )

            var shouldLoop = true
            while (shouldLoop) {
                currentClientSocket = try {
                    currentServerSocket?.accept()
                } catch (e: IOException) {
                    shouldLoop = false
                    null
                }

                currentClientSocket?.let { bluetoothSocket ->
                    val service = BluetoothDataTransferService(bluetoothSocket)
                    _devicesConnected.update {
                        val newDevicesList = it

                        if(newDevicesList.containsKey(bluetoothSocket.remoteDevice.address)){
                            it
                        }else{
                            newDevicesList[bluetoothSocket.remoteDevice.address] = service
                            newDevicesList
                        }

                    }

                    emitAll(
                        service
                            .listenForIncomingMessages()
                            .map {
                                ServerConnectionResult.TransferSucceeded(it)
                            }
                    )
                }
            }
        }.onCompletion {
            closeConnection()
        }.flowOn(Dispatchers.IO)
    }

    @SuppressLint("MissingPermission")
    override suspend fun sendMessageToDevice(address: String, message: Any): Any? {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return null
        }

        val dataTransferService = devicesConnected.value[address]

        if(dataTransferService == null) return null

        dataTransferService.sendMessage(message)

        return message
    }

    override suspend fun sendMessageToAll(message: Any): Any? {
        if (!hasPermission(Manifest.permission.BLUETOOTH_CONNECT)) {
            return null
        }

        for((key, value) in devicesConnected.value.entries){
            sendMessageToDevice(key, message)
        }

        return message
    }

    override fun closeConnection() {
        currentClientSocket?.close()
        currentServerSocket?.close()
        currentClientSocket = null
        currentServerSocket = null
    }

    override fun release() {
        context.unregisterReceiver(bluetoothStateReceiver)
        closeConnection()
    }

    private fun hasPermission(permission: String): Boolean {
        return context.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
    }

    companion object {
        const val SERVICE_UUID = "27b7d1da-08c7-4505-a6d1-2459987e5e2d"
    }
}