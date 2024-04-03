package com.sm.gamecolor.domain

import android.bluetooth.BluetoothDevice

data class ConnectionUIState(
    val foundedDevices: List<BluetoothDevice> = emptyList(),
    val errorMessage: String? = null
)
