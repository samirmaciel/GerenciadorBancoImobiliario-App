package com.samirmaciel.gerenciadorbancoimobiliario.koin

import com.samirmaciel.gerenciadorbancoimobiliario.bluetooth.AndroidBluetoothServerController
import com.samirmaciel.gerenciadorbancoimobiliario.bluetooth.BluetoothServerController
import com.samirmaciel.gerenciadorbancoimobiliario.controller.GameServerController
import com.samirmaciel.gerenciadorbancoimobiliario.ui.GameServerViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreenViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.PlayerViewModel
import com.sm.gamecolor.bluetooth.AndroidBluetoothController
import com.sm.gamecolor.bluetooth.BluetoothController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<GameServerController> { GameServerController(get(), get()) }
    single<BluetoothController> { AndroidBluetoothController(get()) }
    single<BluetoothServerController> { AndroidBluetoothServerController(get()) }
    single { CoroutineScope(Dispatchers.Main) }
    viewModel { InitialScreenViewModel() }
    viewModel { PlayerViewModel(get()) }
    viewModel { GameServerViewModel(get()) }
}