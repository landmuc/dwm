package com.landmuc.dwm.authentication.data.repository

import com.landmuc.dwm.core.remote.SupabaseClient
import com.landmuc.dwm.authentication.domain.remote.AuthenticationRepository
import com.landmuc.dwm.core.remote.SupabaseAuth
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthenticationRepositoryImpl(
    private val auth: SupabaseAuth
): AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            auth.auth.signInWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }

override suspend fun signUp(email: String, password: String): Boolean {
        return try {
            auth.auth.signUpWith(Email) {
                this.email = email
                this.password = password
            }
            true
        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}