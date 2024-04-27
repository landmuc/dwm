package com.landmuc.dwm.authentication.presentation.sign_up

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.authentication.domain.event.SignUpResult
import com.landmuc.dwm.authentication.domain.remote.AuthenticationRepository
import com.landmuc.dwm.authentication.domain.use_case.ConfirmPassword
import com.landmuc.dwm.authentication.domain.use_case.ValidateEmail
import com.landmuc.dwm.authentication.domain.use_case.ValidatePassword
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignUpScreenModel(
    private val authRep: AuthenticationRepository,
    private val validateEmail: ValidateEmail,
    private val validatePassword: ValidatePassword,
    private val confirmPassword: ConfirmPassword
): ScreenModel {

    private val _emailInput = MutableStateFlow("")
    val emailInput: StateFlow<String> = _emailInput.asStateFlow()

    private val _passwordInput = MutableStateFlow("")
    val passwordInput = _passwordInput.asStateFlow()

    private val _passwordConfirmInput = MutableStateFlow("")
    val passwordConfirmInput = _passwordConfirmInput.asStateFlow()


    fun updateEmailInput(email: String) {
        _emailInput.update { email }
    }
    fun updatePasswordInput(password: String) {
        _passwordInput.update { password }
    }
    fun updatePasswordConfirmInput(password: String) {
        _passwordConfirmInput.update { password }
    }

    fun signUp(onResult: (SignUpResult) -> Unit, ) {

        if (!validateEmail(_emailInput.value)) { return onResult(SignUpResult.InvalidEmail) }

        if (!validatePassword(_passwordInput.value)) { return onResult(SignUpResult.InvalidPassword) }

        if (!confirmPassword(_passwordInput.value, _passwordConfirmInput.value)) { return onResult(
            SignUpResult.InvalidPasswordMatch) }

        if (validateEmail(_emailInput.value) &&
            validatePassword(_passwordInput.value) &&
            confirmPassword(_passwordInput.value, _passwordConfirmInput.value)
        ) { return onResult(SignUpResult.ValidCredentials) }
    }

    fun signUpRequest(onResult: (Boolean) -> Unit) {
        screenModelScope.launch {
            val signUpResult =
                authRep.signUp(
                    email = _emailInput.value,
                    password = _passwordInput.value
                )
            onResult(signUpResult)
        }
    }

}



