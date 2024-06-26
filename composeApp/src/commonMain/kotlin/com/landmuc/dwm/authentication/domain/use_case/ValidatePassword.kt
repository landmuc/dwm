package com.landmuc.dwm.authentication.domain.use_case

class ValidatePassword {
    operator fun invoke(
        password: String
    ): Boolean {
        return password.length >= 6
    }
}