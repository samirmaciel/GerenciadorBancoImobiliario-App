package com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerGameRoomScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samirmaciel.gerenciadorbancoimobiliario.R
import com.samirmaciel.gerenciadorbancoimobiliario.ui.AnimationLottie
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTopBar
import com.samirmaciel.gerenciadorbancoimobiliario.ui.GameServerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ServerGameRoomScreen(navController: NavController, viewModel: GameServerViewModel) {

    Scaffold(
        topBar = { CustomTopBar("Jogadores", navController = navController) }
    ) {

        var playerName by remember {
            mutableStateOf("")
        }
        Column(
            modifier = Modifier.padding(top = 70.dp, start = 20.dp, end = 20.dp, bottom = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "Aguardando jogadores 0/6")
                    AnimationLottie(
                        Modifier
                            .size(70.dp)
                            .padding(bottom = 10.dp),
                        R.raw.waiting_player
                    )
                }

            }

            Button(
                modifier = Modifier.fillMaxWidth(),
                enabled = false,
                shape = RoundedCornerShape(5.dp),
                onClick = { /*TODO*/ }) {
                Icon(
                    painter = painterResource(id = R.drawable.send_icon),
                    contentDescription = "Icon Start Game"
                )
                Text(
                    text = "Iniciar Jogo",
                    style = SfProRoundedTypography.titleSmall.copy(color = Color.White)
                )
            }
        }
    }
}
