package com.example.optivote.dbConnection

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.gotrue.FlowType
import io.github.jan.supabase.gotrue.auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.postgrest
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object SupabaseModule{
    @Provides
    @Singleton
    fun provideSupabaseClient(): SupabaseClient{
        return createSupabaseClient(
            supabaseUrl  = "http://192.168.0.94:54321",
            supabaseKey  = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZS1kZW1vIiwicm9sZSI6ImFub24iLCJleHAiOjE5ODM4MTI5OTZ9.CRXP1A7WOeoJeXxjNni43kdQwgnWNReilDMblYTn_I0"
        ){
            install(Postgrest)
            install(Auth){
                flowType = FlowType.PKCE
                scheme="http"
                host="192.168.0.94"

            }
        }
    }
    @Provides
    @Singleton
    fun provideSupabaseDatabase(client: SupabaseClient):Postgrest{
        return client.postgrest
    }
    @Provides
    @Singleton
    fun provideSupabaseAuth(client: SupabaseClient):Auth{
        return client.auth
    }

}