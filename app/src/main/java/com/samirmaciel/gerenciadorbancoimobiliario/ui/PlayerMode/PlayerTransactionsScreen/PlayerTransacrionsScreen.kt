package com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.PlayerTransactionsScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.dto.Mock
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.PlayerPlayersListScreen.TopBar
import com.samirmaciel.gerenciadorbancoimobiliario.ui.TransactionsList

@Composable
fun PlayerTransactionsScreen(){
    Column(modifier = Modifier.fillMaxSize().padding(30.dp)) {
        TopBar(title = "Transações")
        TransactionsList(modifier = Modifier.padding(top = 20.dp), moneyTransactionList = listOf(Mock.getMoneyTransaction()))
    }
}