package com.samirmaciel.gerenciadorbancoimobiliario.domain.models

import android.bluetooth.BluetoothDevice

data class Player(
    val id: String,
    val name: String,
    val type: String,
    val bluetoothDevice: BluetoothDevice,
    val color: String?,
    val image: Int?
)
