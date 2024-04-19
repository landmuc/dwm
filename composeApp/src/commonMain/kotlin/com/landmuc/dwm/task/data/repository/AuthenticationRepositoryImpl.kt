package com.landmuc.dwm.task.data.repository

import com.landmuc.dwm.task.data.remote.SupabaseClient
import com.landmuc.dwm.task.domain.remote.AuthenticationRepository
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.gotrue.providers.builtin.Email

class AuthenticationRepositoryImpl(
    private val client: SupabaseClient
):AuthenticationRepository {
    override suspend fun signIn(email: String, password: String): Boolean {
        return try {
            client.client.auth.signInWith(Email) {
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
            client.client.auth.signUpWith(Email) {
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