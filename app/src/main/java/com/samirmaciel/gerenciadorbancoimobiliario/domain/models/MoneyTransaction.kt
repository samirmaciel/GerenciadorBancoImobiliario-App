package com.samirmaciel.gerenciadorbancoimobiliario.domain.models

import java.time.LocalDateTime
import java.util.Date

data class MoneyTransaction(
    val id: String,
    val playerSender: Player,
    val playerReceiver: Player,
    val value: Double,
    val date: Date = Date()
)
