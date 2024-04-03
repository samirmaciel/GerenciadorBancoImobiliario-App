package com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerHomeScreen


import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samirmaciel.gerenciadorbancoimobiliario.R
import com.samirmaciel.gerenciadorbancoimobiliario.ui.BANK_PANEL_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.BankPanelScreen.BankPanelScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.GameServerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PLAYERS_LIST_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PLAYER_PANEL_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.PlayerPanelScreen.PlayerPanelScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerPlayerPanelScreen.ServerPlayerPanelScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.TRANSACTIONS_SCREEN
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerPlayersListScreen.ServerPlayersListScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerTransactionsScreen.ServerTransactionsScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.SfProRoundedTypography
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.bottomNavigation_bank_disable
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.bottomNavigation_disable
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.dark_yellow
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.light_white


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ServerHomeScreen(viewModel: GameServerViewModel) {

    val navController = rememberNavController()

    Scaffold(bottomBar = {
        CustomBottomNavigation() {
            navController.navigate(it)
        }
    }) {

        NavHost(navController = navController, startDestination = PLAYER_PANEL_SCREEN) {
            composable(PLAYER_PANEL_SCREEN) {
                ServerPlayerPanelScreen(viewModel = viewModel)
            }
            composable(TRANSACTIONS_SCREEN) {
                ServerTransactionsScreen(viewModel = viewModel)
            }
            composable(PLAYERS_LIST_SCREEN) {
                ServerPlayersListScreen(viewModel = viewModel)
            }
            composable(BANK_PANEL_SCREEN) {
                BankPanelScreen(viewModel = viewModel)
            }

        }
    }

}


@Composable
fun CustomBottomNavigation(onSelection: (String) -> Unit) {

    var currentSelection by remember {
        mutableStateOf(PLAYER_PANEL_SCREEN)
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(color = Color.White)
            .padding(start = 10.dp, end = 10.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp)
                .background(color = light_white, RoundedCornerShape(10.dp)),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                IconButton(onClick = {
                    currentSelection = PLAYER_PANEL_SCREEN
                    onSelection(currentSelection)
                }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.home_icon),
                            contentDescription = "Home",
                            tint = if (currentSelection != PLAYER_PANEL_SCREEN) bottomNavigation_disable else Color.Black
                        )
                        Text(
                            text = "Inicio",
                            style = SfProRoundedTypography.titleSmall.copy(color = if (currentSelection != PLAYER_PANEL_SCREEN) bottomNavigation_disable else Color.Black)
                        )
                    }
                }
                IconButton(onClick = {
                    currentSelection = TRANSACTIONS_SCREEN
                    onSelection(currentSelection)
                }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.arrows_icon),
                            contentDescription = "Transações",
                            tint = if (currentSelection != TRANSACTIONS_SCREEN) bottomNavigation_disable else Color.Black
                        )
                        Text(
                            text = "Transações",
                            style = SfProRoundedTypography.titleSmall.copy(color = if (currentSelection != TRANSACTIONS_SCREEN) bottomNavigation_disable else Color.Black)
                        )
                    }
                }
                IconButton(onClick = {
                    currentSelection = PLAYERS_LIST_SCREEN
                    onSelection(currentSelection)
                }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.contacts_icon),
                            contentDescription = "Jogadores",
                            tint = if (currentSelection != PLAYERS_LIST_SCREEN) bottomNavigation_disable else Color.Black
                        )
                        Text(
                            text = "Jogadores",
                            style = SfProRoundedTypography.titleSmall.copy(color = if (currentSelection != PLAYERS_LIST_SCREEN) bottomNavigation_disable else Color.Black)
                        )
                    }
                }


                IconButton(onClick = {
                    currentSelection = BANK_PANEL_SCREEN
                    onSelection(currentSelection)
                }) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            painter = painterResource(id = R.drawable.bank_icon),
                            contentDescription = "Banco",
                            tint = if (currentSelection != BANK_PANEL_SCREEN) bottomNavigation_bank_disable else dark_yellow
                        )
                        Text(
                            text = "Banco",
                            style = SfProRoundedTypography.titleSmall.copy(color = if (currentSelection != BANK_PANEL_SCREEN) bottomNavigation_bank_disable else dark_yellow)
                        )
                    }
                }


            }
        }
    }

}

