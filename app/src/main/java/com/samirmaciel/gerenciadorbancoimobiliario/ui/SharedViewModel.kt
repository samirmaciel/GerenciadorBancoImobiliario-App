package com.samirmaciel.gerenciadorbancoimobiliario.ui

import androidx.lifecycle.ViewModel
import com.sm.gamecolor.bluetooth.BluetoothController
import com.sm.gamecolor.bluetooth.DeviceConnectionState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SharedViewModel(val bluetoothController: BluetoothController): ViewModel() {

    private var playerName: String? = null
    private var isBank: Boolean = false

    private val _DeviceConnectionState = MutableStateFlow(DeviceConnectionState())
    val deviceConnectionState get() = _DeviceConnectionState.asStateFlow()

    fun getPlayerName() = playerName

    fun setPlayerName(name: String){
        playerName = name
    }
    fun getIsBank() = isBank

    fun setIsBank(isBank: Boolean){
        this.isBank = isBank
    }
}