package com.samirmaciel.gerenciadorbancoimobiliario.ui

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.ConnectionScreen.ConnectionScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.PlayerGameRoomScreen.PlayerGameRoomScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerMode.PlayerHomeScreen.PlayerHomeScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.ServerMode.ServerHomeScreen.ServerHomeScreen
import com.samirmaciel.gerenciadorbancoimobiliario.ui.theme.GerenciadorBancoImobiliarioTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {

    private val bluetoothManager by lazy {
        applicationContext.getSystemService(BluetoothManager::class.java)
    }
    private val bluetoothAdapter by lazy {
        bluetoothManager?.adapter
    }

    private val isBluetoothEnabled: Boolean
        get() = bluetoothAdapter?.isEnabled == true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getBluetoothPermissions()

        setContent {
            GerenciadorBancoImobiliarioTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    val playerViewModel by viewModel<PlayerViewModel>()

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = INITIAL_SCREEN) {
                        composable(INITIAL_SCREEN) {
                            InitialScreen(playerViewModel, onNewGame = {
                                navController.navigate(SERVER_GAME_ROOM_SCREEN)
                            }, onEnterGame = {
                                navController.navigate(CONNECTION_SCREEN)
                            })
                        }
                        composable(CONNECTION_SCREEN) {
                            ConnectionScreen(navController, playerViewModel)
                        }
                        composable(PLAYER_GAME_ROOM_SCREEN) {
                            PlayerGameRoomScreen(navController = navController, playerViewModel)
                        }
                        composable(SERVER_GAME_ROOM_SCREEN) {
                            val gameServerViewModel by viewModel<GameServerViewModel>()
                            ServerGameRoomScreen(navController = navController, gameServerViewModel)
                        }
                        composable(PLAYER_HOME_SCREEN) {
                            PlayerHomeScreen(playerViewModel)
                        }
                        composable(SERVER_HOME_SCREEN) {
                            val gameServerViewModel by viewModel<GameServerViewModel>()
                            ServerHomeScreen(gameServerViewModel)
                        }

                    }

                }
            }
        }
    }

    private fun getBluetoothPermissions() {
        val enableBluetoothLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { /* Not needed */ }

        val permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { perms ->
            val canEnableBluetooth = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                perms[Manifest.permission.BLUETOOTH_CONNECT] == true
            } else true

            if (canEnableBluetooth && !isBluetoothEnabled) {
                enableBluetoothLauncher.launch(
                    Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE)
                )
            }
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            permissionLauncher.launch(
                arrayOf(
                    Manifest.permission.BLUETOOTH_SCAN,
                    Manifest.permission.BLUETOOTH_CONNECT,
                )
            )
        }
    }
}
