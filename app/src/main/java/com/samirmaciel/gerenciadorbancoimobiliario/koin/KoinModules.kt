package com.samirmaciel.gerenciadorbancoimobiliario.koin

import com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen.InitialScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val appModule = module {
    viewModel { InitialScreenViewModel() }
}