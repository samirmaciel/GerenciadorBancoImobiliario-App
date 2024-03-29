package com.samirmaciel.gerenciadorbancoimobiliario.domain.models

data class Player(
    val id: String,
    val name: String,
    val type: String,
    val color: String?,
    val image: Int?
)
