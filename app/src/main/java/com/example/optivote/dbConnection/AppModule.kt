package com.example.optivote.dbConnection

import com.example.optivote.repository.AuthenticationRepository
import com.example.optivote.repository.AuthenticationRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import io.github.jan.supabase.gotrue.Auth

@Module
@InstallIn(ViewModelComponent::class)
object AppModule {
    @Provides
    fun provideLoginRepesetory(auth:Auth): AuthenticationRepository = AuthenticationRepositoryImpl(auth)
}