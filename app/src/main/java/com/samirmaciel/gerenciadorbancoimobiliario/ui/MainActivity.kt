package com.samirmaciel.gerenciadorbancoimobiliario.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ConnectionScreen.ConnectionScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.HomeScreen.HomeScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.GameRoomScreen.GameRoomScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.GerenciadorBancoImobiliarioTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadorBancoImobiliarioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val sharedViewModel by viewModel<SharedViewModel>()
                    
                   val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = HOME_SCREEN ){
                        composable(INITIAL_SCREEN){
                            InitialScreen(sharedViewModel, onNewGame = {
                                navController.navigate(GAME_ROOM_SCREEN)
                            }, onEnterGame = {
                                navController.navigate(CONNECTION_SCREEN)
                            })
                        }
                        composable(CONNECTION_SCREEN){
                            ConnectionScreen(navController, sharedViewModel)
                        }
                        composable(GAME_ROOM_SCREEN){
                            GameRoomScreen(navController = navController, sharedViewModel)
                        }
                        composable(HOME_SCREEN){
                            HomeScreen(sharedViewModel)
                        }

                    }

                }
            }
        }
    }
}
