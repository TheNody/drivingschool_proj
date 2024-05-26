package com.example.drivingschool76.data.network

import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

object SupabaseClient {

    val client = createSupabaseClient(
        supabaseUrl = "https://bsnvunuptefptcvhfofb.supabase.co",
        supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImJzbnZ1bnVwdGVmcHRjdmhmb2ZiIiwicm9sZSI6ImFub24iLCJpYXQiOjE3MTMzNzAzNzEsImV4cCI6MjAyODk0NjM3MX0.QivBDkvG3nbkJaT_4AZceeECpiDVUpXpi_i7kYEi1PM"
    ) {
        install(Auth)
        install(Postgrest)
        install(Storage)
    }
}