package com.sm.gamecolor.domain

import android.bluetooth.BluetoothDevice
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player

data class ScanUiState(
    val scannedDevices: List<BluetoothDevice> = emptyList(),
    val foundedDevices: Boolean = false,
    val errorMessage: String? = null,
    val receivedLine: Player? = null
)
