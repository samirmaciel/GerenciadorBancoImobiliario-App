package com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen


import android.inputmethodservice.Keyboard
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
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
            .weight(1f)
            .padding(top = 50.dp), text = "Gerenciador Banco Imobiliário", style = SfProRoundedTypography.titleLarge)

        CustomTextField(modifier = Modifier
            .fillMaxWidth(), hint = "Seu nome", value = userName, onTextChange = { text ->
            userName = text
            viewModel.validateUserName(userName)
        })

        Row(modifier = Modifier
            .weight(1f)
            .padding(top = 10.dp), horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.Top) {
            Button(modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors( containerColor = blue), enabled = userNameIsValid.value, shape = RoundedCornerShape(5.dp), onClick = { onNewGame(userName) }) {
                Text(text = "Novo jogo", style = SfProRoundedTypography.labelMedium)
            }
            Button(modifier = Modifier.weight(1f), colors = ButtonDefaults.buttonColors( containerColor = dark_yellow), enabled = userNameIsValid.value, shape = RoundedCornerShape(5.dp), onClick = {onEnterGame(userName)}) {
                Text(text = "Entrar em um jogo", style = SfProRoundedTypography.labelMedium.copy(color = if(userNameIsValid.value) Color.Black else Color.Gray))
            }
        }


    }
}

@Composable
fun CustomTextField(
    modifier: Modifier,
    hint: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
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
        onValueChange = onTextChange,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        )
    )
}