package com.samirmaciel.gerenciadorbancoimobiliario.domain.models

import android.bluetooth.BluetoothDevice
import androidx.compose.ui.graphics.Color
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_white

data class Player(
    val id: String,
    val name: String,
    val type: String,
    val bluetoothDevice: BluetoothDevice?,
    var color: Color = light_white,
    var image: Int? = null
)
