package com.landmuc.dwm.authentication.domain.use_case

class ConfirmPassword {
    operator fun invoke(
        password: String,
        confirmPassword: String
    ): Boolean {
        return password == confirmPassword
    }
}