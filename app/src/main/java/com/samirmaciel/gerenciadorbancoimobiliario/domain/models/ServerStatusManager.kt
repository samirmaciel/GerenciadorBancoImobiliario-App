package com.samirmaciel.gerenciadorbancoimobiliario.domain.models

import com.sm.gamecolor.domain.ConnectionState

data class ServerStatusManager(
    var message: Any? = null,
    var errorMessage: String? = null
)
