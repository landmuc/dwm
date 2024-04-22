package com.landmuc.dwm.task.domain

sealed class SignUpResult {
    data object InvalidEmail: SignUpResult()
    data object InvalidPassword: SignUpResult()
    data object InvalidPasswordMatch: SignUpResult()
    data object ValidCredentials: SignUpResult()
}