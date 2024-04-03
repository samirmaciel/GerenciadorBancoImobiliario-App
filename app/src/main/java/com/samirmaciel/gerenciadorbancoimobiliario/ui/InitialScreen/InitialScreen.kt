package com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTextField
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.dark_yellow


@Composable
fun InitialScreen(
    viewModel: PlayerViewModel,
    onNewGame: () -> Unit,
    onEnterGame: () -> Unit
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(30.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var playerName by remember { mutableStateOf(viewModel.getPlayerName() ?: "") }

        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp),
            text = "Gerenciador Banco Imobiliário",
            style = SfProRoundedTypography.titleLarge
        )


        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            CustomTextField(modifier = Modifier
                .fillMaxWidth(), hint = "Seu nome", value = playerName, onTextChange = { text ->
                playerName = text
            })

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                colors = ButtonDefaults.buttonColors(containerColor = blue),
                enabled = playerName.isNotBlank(),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    onNewGame()
                    viewModel.setPlayerName(playerName)
                }) {
                Text(text = "Criar um novo Jogo", style = SfProRoundedTypography.labelMedium)
            }
            Button(
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = dark_yellow),
                enabled = playerName.isNotBlank(),
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    onEnterGame()
                    viewModel.setPlayerName(playerName)
                }) {
                Text(
                    text = "Entrar em um jogo",
                    style = SfProRoundedTypography.labelMedium.copy(color = if (playerName.isNotBlank()) Color.Black else Color.Gray)
                )
            }
        }

    }
}

