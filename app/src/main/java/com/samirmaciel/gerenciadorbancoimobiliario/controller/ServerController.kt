package com.samirmaciel.gerenciadorbancoimobiliario.controller

interface ServerController {

    suspend fun updateAllPlayers(message: Any?)
    suspend fun updatePlayer(message: Any?)

}