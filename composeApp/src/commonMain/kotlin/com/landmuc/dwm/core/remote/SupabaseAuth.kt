package com.landmuc.dwm.core.remote

import io.github.jan.supabase.gotrue.auth


class SupabaseAuth(client: SupabaseClient) {
    val auth = client.supabaseClient.auth
}