package com.sm.gamecolor.bluetooth

import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import com.sm.gamecolor.domain.ConnectionState


data class DeviceConnectionState(
    var connectionState: ConnectionState? = null,
    var receivedLine: Player? = null,
    var errorMessage: String? = null
)
