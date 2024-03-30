package com.samirmaciel.gerenciadorbancoimobiliario.koin

import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreenViewModel
import com.samirmaciel.gerenciadorbancoimobiliario.ui.SharedViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel { InitialScreenViewModel() }
    viewModel { SharedViewModel() }
}