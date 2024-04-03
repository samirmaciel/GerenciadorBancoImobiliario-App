package com.samirmaciel.gerenciadorbancoimobiliario.bluetooth

import com.sm.gamecolor.bluetooth.TransferConnectionResult


sealed interface ServerConnectionResult {

    data class TransferSucceeded(val message: Any): ServerConnectionResult
    data class Error(val message: String): ServerConnectionResult
}