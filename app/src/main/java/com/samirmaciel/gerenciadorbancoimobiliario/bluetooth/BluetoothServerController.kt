package com.samirmaciel.gerenciadorbancoimobiliario.bluetooth

import android.bluetooth.BluetoothDevice
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import com.sm.gamecolor.bluetooth.BluetoothDataTransferService
import com.sm.gamecolor.bluetooth.TransferConnectionResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow

interface BluetoothServerController {
    val devicesConnected: StateFlow<MutableMap<String, BluetoothDataTransferService>>
    val errors: SharedFlow<String>

    fun startBluetoothServer(): Flow<ServerConnectionResult>

    suspend fun sendMessageToDevice(address: String, message: Any): Any?
    suspend fun sendMessageToAll(message: Any): Any?

    fun closeConnection()
    fun release()
}
