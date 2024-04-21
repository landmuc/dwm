package com.landmuc.dwm.task.presentation.sign_in

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.landmuc.dwm.task.data.remote.SupabaseClient
import com.landmuc.dwm.task.data.repository.AuthenticationRepositoryImpl
import com.landmuc.dwm.task.domain.remote.AuthenticationRepository
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SignInScreenModel(
    private val authRep: AuthenticationRepository
): ScreenModel {

    private val _emailInput = MutableStateFlow("")
    val emailInput = _emailInput.asStateFlow()

    private val _passwordInput = MutableStateFlow("")
    val passwordInput = _passwordInput.asStateFlow()

    fun updateEmailInput(email: String) {
        _emailInput.update { email }
    }
    fun updatePasswordInput(password: String) {
        _passwordInput.update { password }
    }

    fun signIn(onResult: (Boolean) -> Unit ) {
        screenModelScope.launch {
            val signInResult =
                authRep.signIn(
                    email = _emailInput.value,
                    password = _passwordInput.value
                )
            onResult( signInResult )
        }
    }
}
