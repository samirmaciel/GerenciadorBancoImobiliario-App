package com.samirmaciel.gerenciadorbancoimobiliario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samirmaciel.gerenciadorbancoimobiliario.bluetooth.AndroidBluetoothServerController
import com.sm.gamecolor.bluetooth.BluetoothController
import com.sm.gamecolor.domain.ConnectionUIState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class PlayerViewModel(private val bluetoothController: BluetoothController): ViewModel() {

    private var playerName: String? = null
    private val _connectionUIState = MutableStateFlow(ConnectionUIState())

    val connectionUIState = combine(
        bluetoothController.scannedDevices,
        _connectionUIState
    ) { scannedDevices, state ->
        state.copy(
            foundedDevices = scannedDevices
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _connectionUIState.value)


    init {

        bluetoothController.errors.onEach { error ->
            _connectionUIState.update { it.copy(
                errorMessage = error
            ) }
        }.launchIn(viewModelScope)
    }

    fun getPlayerName() = playerName

    fun setPlayerName(name: String){
        playerName = name
    }

    fun startScan() {
        bluetoothController.startDiscovery()
    }

    fun stopScan() {
        bluetoothController.stopDiscovery()
    }

    override fun onCleared() {
        super.onCleared()
        bluetoothController.release()
    }
}