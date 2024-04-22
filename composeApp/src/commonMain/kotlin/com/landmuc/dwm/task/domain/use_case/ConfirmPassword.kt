package com.landmuc.dwm.task.domain.use_case

class ConfirmPassword {
    operator fun invoke(
        password: String,
        confirmPassword: String
    ): Boolean {
        return password == confirmPassword
    }
}