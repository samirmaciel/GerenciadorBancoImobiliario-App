package com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayersListScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography

@Composable
fun PlayersListScreen(){
    Column() {
        TopBar()
        

    }
}

@Composable
fun TopBar(modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
            Text(
                text = "Jogadores",
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