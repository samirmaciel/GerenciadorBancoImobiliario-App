package com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerPlayersListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.dto.Mock
import com.samirmaciel.gerenciadorbancoimobiliario.ui.GameServerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerCard
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography

@Composable
fun ServerPlayersListScreen(viewModel: GameServerViewModel) {
    Column() {
        TopBar(title = "Jogadores")
        LazyColumn(modifier = Modifier.padding(top = 20.dp)) {
            items(Mock.getPLayerList()) { player ->
                PlayerCard(player = player, onSendMoney = {})
            }
        }
    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier, title: String) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
        , contentAlignment = Alignment.BottomCenter
    ) {

        Row(modifier = Modifier.matchParentSize(), horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = title,
                color = Color.Black,
                style = SfProRoundedTypography.titleMedium
            )
        }
        Divider(
            color = Color.Gray, modifier = Modifier
                .fillMaxWidth()
                .height(0.5.dp)
        )
    }
}