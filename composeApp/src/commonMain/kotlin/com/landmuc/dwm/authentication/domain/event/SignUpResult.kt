package com.landmuc.dwm.authentication.domain.event

sealed class SignUpResult {
    data object InvalidEmail: SignUpResult()
    data object InvalidPassword: SignUpResult()
    data object InvalidPasswordMatch: SignUpResult()
    data object ValidCredentials: SignUpResult()
}