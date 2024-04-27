package com.landmuc.dwm.core.remote

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.gotrue.Auth

object SupabaseClient {
    val supabaseClient = createSupabaseClient(
        supabaseUrl = "https://oefnvzosyertqmxxtbao.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Im9lZm52em9zeWVydHFteHh0YmFvIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTMxMDIzNDcsImV4cCI6MjAyODY3ODM0N30.OCJErdm0mRTjTPm9sblbscQzEUC37dKLx9W4H9pQ00w"
    ) {
        install(Postgrest)
        install(Auth)
        //install other modules
    }
}