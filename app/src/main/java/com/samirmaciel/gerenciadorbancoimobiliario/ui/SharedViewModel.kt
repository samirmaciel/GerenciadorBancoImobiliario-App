package com.samirmaciel.gerenciadorbancoimobiliario.ui

import androidx.lifecycle.ViewModel

class SharedViewModel: ViewModel() {

    private var playerName: String? = null

    fun getPlayerName() = playerName

    fun setPlayerName(name: String){
        playerName = name
    }
}