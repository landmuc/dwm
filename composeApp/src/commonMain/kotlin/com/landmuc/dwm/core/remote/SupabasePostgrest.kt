package com.landmuc.dwm.core.remote

import io.github.jan.supabase.postgrest.postgrest

class SupabasePostgrest(client: SupabaseClient) {
    val postgrest = client.supabaseClient.postgrest
}