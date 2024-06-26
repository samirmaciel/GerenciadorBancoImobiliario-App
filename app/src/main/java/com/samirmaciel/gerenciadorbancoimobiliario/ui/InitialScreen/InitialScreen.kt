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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTextField
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.dark_yellow


@Composable
fun InitialScreen(viewModel: InitialScreenViewModel, onNewGame: (String) -> Unit, onEnterGame: (String) -> Unit){

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(30.dp), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {

        var userName by remember { mutableStateOf("") }
        val userNameIsValid = viewModel.userNameIsValid.collectAsState()

        viewModel.validateUserName(userName)

        Text(modifier = Modifier
            .fillMaxWidth()
            .padding(top = 50.dp), text = "Gerenciador Banco Imobiliário", style = SfProRoundedTypography.titleLarge)


        Column(modifier = Modifier.weight(1f), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            CustomTextField(modifier = Modifier
                .fillMaxWidth(), hint = "Seu nome", value = userName, onTextChange = { text ->
                userName = text
                viewModel.validateUserName(userName)
            })
            Button(modifier = Modifier.fillMaxWidth().padding(top = 10.dp), colors = ButtonDefaults.buttonColors( containerColor = blue), enabled = userNameIsValid.value, shape = RoundedCornerShape(5.dp), onClick = { onNewGame(userName) }) {
                Text(text = "Criar um novo Jogo", style = SfProRoundedTypography.labelMedium)
            }
            Button(modifier = Modifier.fillMaxWidth(), colors = ButtonDefaults.buttonColors( containerColor = dark_yellow), enabled = userNameIsValid.value, shape = RoundedCornerShape(5.dp), onClick = {onEnterGame(userName)}) {
                Text(text = "Entrar em um jogo", style = SfProRoundedTypography.labelMedium.copy(color = if(userNameIsValid.value) Color.Black else Color.Gray))
            }
        }

    }
}

