package com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography


@Composable
fun InitialScreen(){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        var userName by remember { mutableStateOf("") }

        Text(modifier = Modifier.fillMaxWidth().weight(1f).padding(top = 50.dp), text = "Gerenciador Banco Imobiliário", style = SfProRoundedTypography.titleLarge)

        CustomTextField(modifier = Modifier
            .fillMaxWidth(), hint = "Seu nome", value = userName) {
            userName = it
        }

        Row(modifier = Modifier.weight(1f), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Bottom) {
            Button(modifier = Modifier.weight(1f), shape = RoundedCornerShape(5.dp), onClick = { /*TODO*/ }) {
                Text(text = "Novo jogo", style = SfProRoundedTypography.labelMedium)
            }
            Button(modifier = Modifier.weight(1f), shape = RoundedCornerShape(5.dp), onClick = { /*TODO*/ }) {
                Text(text = "Entrar em um jogo", style = SfProRoundedTypography.labelMedium)
            }
        }


    }
}

@Composable
fun CustomTextField(
    modifier: Modifier,
    hint: String,
    value: String,
    onTextChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = modifier, colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color.Black,
            unfocusedBorderColor = Color.Black,
        ), label = {
                Text(
                    text = hint,
                    style = SfProRoundedTypography.labelSmall.copy(color = Color.Gray),
                )
        }, value = value,
        onValueChange = onTextChange
    )
}