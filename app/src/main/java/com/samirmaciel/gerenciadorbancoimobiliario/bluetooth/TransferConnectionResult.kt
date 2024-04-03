package com.sm.gamecolor.bluetooth

import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player


sealed interface TransferConnectionResult {
    object ConnectionEstablished: TransferConnectionResult
    data class TransferSucceeded(val message: Any): TransferConnectionResult
    data class Error(val message: String): TransferConnectionResult
}
