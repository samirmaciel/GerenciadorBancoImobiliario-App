package com.samirmaciel.gerenciadorbancoimobiliario.koin

import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreenViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.SharedViewModel
import com.sm.gamecolor.bluetooth.AndroidBluetoothController
import com.sm.gamecolor.bluetooth.BluetoothController
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    single<BluetoothController> { AndroidBluetoothController(get()) }
    viewModel { InitialScreenViewModel() }
    viewModel { SharedViewModel(get()) }
}