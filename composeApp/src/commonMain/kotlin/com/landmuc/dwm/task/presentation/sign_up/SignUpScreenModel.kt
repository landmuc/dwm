package com.landmuc.dwm.task.presentation.sign_up

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.task.domain.remote.AuthenticationRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpScreenModel(
    private val authRep: AuthenticationRepository
): ScreenModel {

    private val _emailInput = MutableStateFlow("")
    val emailInput: StateFlow<String> = _emailInput.asStateFlow()

    private val _passwordInput = MutableStateFlow("")
    val passwordInput = _passwordInput.asStateFlow()

    private val _passwordConfirmInput = MutableStateFlow("")
    val passwordConfirmInput = _passwordConfirmInput.asStateFlow()

    private val _validSignUp = MutableStateFlow(false)
    val validSignUp = _validSignUp.asStateFlow()

    fun updateEmailInput(email: String) {
        _emailInput.update { email }
    }
    fun updatePasswordInput(password: String) {
        _passwordInput.update { password }
    }
    fun updatePasswordConfirmInput(password: String) {
        _passwordConfirmInput.update { password }
    }

    fun signUp() {
        screenModelScope.launch {
            val signOutJob = async {
                authRep.signUp(
                    email = _emailInput.value,
                    password = _passwordInput.value
                )
            }
            _validSignUp.update { signOutJob.await() }
        }
    }

    fun setValidSignUpFalse() {
        _validSignUp.update { false }
    }

}

