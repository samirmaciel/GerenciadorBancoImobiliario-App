package com.samirmaciel.gerenciadorbancoimobiliario.ui.ConnectionScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTopBar
import com.samirmaciel.gerenciadorbancoimobiliario.ui.SharedViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConnectionScreen(navController: NavController, viewModel: SharedViewModel) {

    Scaffold(topBar = { CustomTopBar("Conexão", navController) }) {

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
                text = "Olá, ${viewModel.getPlayerName()}!",
                color = Color.Companion.Black,
                style = SfProRoundedTypography.titleLarge
            )

            LazyColumn {

            }
        }
    }
}