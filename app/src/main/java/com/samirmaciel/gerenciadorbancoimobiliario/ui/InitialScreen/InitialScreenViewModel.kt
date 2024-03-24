package com.samirmaciel.gerenciadorbancoimobiliario.ui.InitialScreen

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class InitialScreenViewModel : ViewModel() {

    private val _userNameIsValid = MutableStateFlow(false)
    var userNameIsValid = _userNameIsValid.asStateFlow()


    fun validateUserName(userName: String){
        _userNameIsValid.value = userName.isNotBlank()
    }
}