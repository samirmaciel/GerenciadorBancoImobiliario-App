package com.samirmaciel.gerenciadorbancoimobiliario

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
import com.samirmaciel.gerenciadorbancoimobiliario.ui.BANK_HOME_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ENTER_ROOM_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.EnterRoomScreen.EnterRoomScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.INITIAL_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreenViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PLAYERS_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PLAYER_HOME_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.SEND_MONEY_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.TRANSACTION_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.GerenciadorBancoImobiliarioTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GerenciadorBancoImobiliarioTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    
                   val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = INITIAL_SCREEN ){
                        composable(INITIAL_SCREEN){

                            val viewModel by viewModel<InitialScreenViewModel>()

                            InitialScreen(viewModel, onNewGame = {userName ->
                                navController.navigate(PLAYERS_SCREEN + "/$userName")
                            }, onEnterGame = {userName ->
                                navController.navigate(ENTER_ROOM_SCREEN + "/$userName")
                            })
                        }
                        composable("$PLAYER_HOME_SCREEN/{roomID}"){

                        }
                        composable("$PLAYERS_SCREEN/{roomID}"){

                        }
                        composable(BANK_HOME_SCREEN){

                        }
                        composable(TRANSACTION_SCREEN){

                        }
                        composable(SEND_MONEY_SCREEN){

                        }
                        composable("$ENTER_ROOM_SCREEN/{userName}"){
                            val userName = it.arguments?.getString("userName")
                            EnterRoomScreen(userName, navController)
                        }
                    }

                }
            }
        }
    }
}
