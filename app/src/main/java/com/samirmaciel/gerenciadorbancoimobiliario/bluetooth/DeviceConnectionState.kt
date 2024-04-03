package com.sm.gamecolor.bluetooth

import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import com.sm.gamecolor.domain.ConnectionState


sealed interface DeviceConnectionState{
    data class TransferSucceeded(val message: Any): TransferConnectionResult
    data class Error(val message: String): TransferConnectionResult
}
