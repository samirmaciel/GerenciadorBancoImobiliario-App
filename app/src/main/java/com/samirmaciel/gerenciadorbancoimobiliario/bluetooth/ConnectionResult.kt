package com.sm.gamecolor.bluetooth

import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player


sealed interface ConnectionResult {
    object ConnectionEstablished: ConnectionResult
    data class TransferSucceeded(val line: Player): ConnectionResult
    data class Error(val message: String): ConnectionResult
}