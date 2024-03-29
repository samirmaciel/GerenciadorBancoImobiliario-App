package com.samirmaciel.gerenciadorbancoimobiliario.dto

import android.bluetooth.BluetoothDevice
import androidx.compose.ui.graphics.Color
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.MoneyTransaction
import com.samirmaciel.gerenciadorbancoimobiliario.domain.models.Player
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue

object Mock {


    fun getMoneyTransaction(): MoneyTransaction{
        val players = getTwoPlayers()
        return MoneyTransaction("25", players[0], players[1], 10000.0)
    }

    fun getTwoPlayers(): List<Player> {
        return listOf(Player(id = "1", name = "Francisco", type = "Jogador", bluetoothDevice = null, color = Color.Green, image = null ),
            Player(id = "2", name = "Carlos", type = "Jogador", bluetoothDevice = null, color = Color.Cyan, image = null )
        )
    }
}