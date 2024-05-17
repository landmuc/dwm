package com.landmuc.dwm.authentication.presentation.sign_in

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.authentication.domain.remote.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInViewModel(
    private val authRep: AuthenticationRepository
): ViewModel() {

    private val _emailInput = MutableStateFlow("test@test.com")
    val emailInput = _emailInput.asStateFlow()

    private val _passwordInput = MutableStateFlow("test123")
    val passwordInput = _passwordInput.asStateFlow()

    fun updateEmailInput(email: String) {
        _emailInput.update { email }
    }
    fun updatePasswordInput(password: String) {
        _passwordInput.update { password }
    }

    fun signIn(onResult: (Boolean) -> Unit ) {
        viewModelScope.launch {
            val signInResult =
                authRep.signIn(
                    email = _emailInput.value,
                    password = _passwordInput.value
                )
            onResult( signInResult )
        }
    }
}

