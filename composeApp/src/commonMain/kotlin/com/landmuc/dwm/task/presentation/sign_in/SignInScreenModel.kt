package com.landmuc.dwm.task.presentation.sign_in

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.task.data.remote.SupabaseClient
import com.landmuc.dwm.task.data.repository.AuthenticationRepositoryImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInScreenModel(): ScreenModel {
    private val client = SupabaseClient
    private val authRep = AuthenticationRepositoryImpl(client)

    private val _emailInput = MutableStateFlow("")
    val emailInput: StateFlow<String> = _emailInput.asStateFlow()

    private val _passwordInput = MutableStateFlow("")
    val passwordInput = _passwordInput.asStateFlow()

    fun updateEmailInput(email: String) {
        _emailInput.update { email }
    }
    fun updatePasswordInput(password: String) {
        _passwordInput.update { password }
    }

    fun testSignIn() {
        screenModelScope.launch {
            authRep.signIn(
                email = _emailInput.value,
                password = _passwordInput.value
            )
        }
    }
}

