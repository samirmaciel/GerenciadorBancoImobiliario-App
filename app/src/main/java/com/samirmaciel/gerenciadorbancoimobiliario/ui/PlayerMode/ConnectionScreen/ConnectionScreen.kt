package com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.ConnectionScreen

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.samirmaciel.gerenciadorbancoimobiliario.ui.CustomTopBar
import com.samirmaciel.gerenciadorbancoimobiliario.ui.DeviceConnectionCard
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ConnectionScreen(navController: NavController, viewModel: PlayerViewModel) {

    viewModel.startScan()
    val connectionUIState by viewModel.connectionUIState.collectAsState()

    Scaffold(topBar = { CustomTopBar("Conexão", navController) },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize().padding(top = 50.dp, start = 30.dp, end = 30.dp, bottom = 30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .align(Alignment.Start).padding(30.dp),
                    text = "Olá, ${viewModel.getPlayerName()}!",
                    color = Color.Companion.Black,
                    style = SfProRoundedTypography.titleLarge
                )

                if(connectionUIState.foundedDevices.isNotEmpty()){
                    LazyColumn() {
                        items(connectionUIState.foundedDevices){
                            DeviceConnectionCard(it){

                            }
                        }
                    }
                }else{
                    CircularProgressIndicator(modifier = Modifier.padding(30.dp))
                }


            }
        }
        )

}