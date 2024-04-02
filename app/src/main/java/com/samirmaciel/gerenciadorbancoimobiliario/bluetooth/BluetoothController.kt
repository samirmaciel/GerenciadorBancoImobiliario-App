package com.sm.gamecolor.bluetooth

import android.bluetooth.BluetoothDevice
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothController {
    val isConnected: StateFlow<Boolean>
    val scannedDevices: StateFlow<List<BluetoothDevice>>
    val errors: SharedFlow<String>

    fun startDiscovery()
    fun stopDiscovery()

    fun startBluetoothServer(): Flow<TransferConnectionResult>
    fun connectToDevice(device: BluetoothDevice): Flow<TransferConnectionResult>

    suspend fun trySendLine(line: Player): Player?

    fun closeConnection()
    fun release()
}