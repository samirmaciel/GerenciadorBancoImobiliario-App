package com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.BankPanelScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.dto.Mock
import com.samirmaciel.gerenciadorbancoimobiliario.ui.GameServerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerCard
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.dark_yellow
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_white

@Composable
fun BankPanelScreen(viewModel: GameServerViewModel){
    Column {
        Header()
        Content(modifier = Modifier
            .padding(30.dp))
    }
}

@Composable
fun Header() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(200.dp)
            .background(color = dark_yellow)
    ) {
        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(30.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f), verticalAlignment = Alignment.Top
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = "Painel do Banco",
                    style = SfProRoundedTypography.titleMedium.copy(color = Color.White)
                )
            }
            Text(
                text = "Fundo Bancário",
                style = SfProRoundedTypography.labelSmall.copy(color = light_white)
            )
            Text(
                text = "$2.000.000.000",
                style = SfProRoundedTypography.titleLarge.copy(color = Color.White)
            )

        }
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {

        Text(
            modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
            text = "Jogadores",
            style = SfProRoundedTypography.titleSmall
        )

        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(Mock.getPLayerList()) { player ->
                PlayerCard(player = player, onSendMoney = {})
            }
        }
    }
}