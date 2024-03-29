package com.samirmaciel.gerenciadorbancoimobiliario.ui.EnterRoomScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTextField
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTopBar
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PLAYERS_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.blue


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun EnterRoomScreen(userName: String?, navController: NavController) {

    Scaffold(topBar = { CustomTopBar("Room Enter", navController) }) {

        var roomID by remember {
            mutableStateOf("")
        }

        var buttonIsEnabled by remember {
            mutableStateOf(false)
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.Start),
                text = "Olá ${userName}",
                color = Color.Companion.Black,
                style = SfProRoundedTypography.titleLarge
            )
            CustomTextField(
                modifier = Modifier.fillMaxWidth(),
                hint = "ID da Sala",
                value = roomID,
                keyboardType = KeyboardType.Number
            ) {
                roomID = it
                buttonIsEnabled = it.isNotBlank()
            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(containerColor = blue),
                enabled = buttonIsEnabled,
                shape = RoundedCornerShape(5.dp),
                onClick = {
                    navController.navigate(
                        "$PLAYERS_SCREEN/${roomID}"
                    )
                }) {
                Text(text = "Entrar na Sala", style = SfProRoundedTypography.labelMedium)
            }
        }

    }
}