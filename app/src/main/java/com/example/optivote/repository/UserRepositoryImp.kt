package com.example.optivote.repository

import com.example.optivote.model.UserDto
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImp @Inject constructor(private val auth:Auth ,private val postgrest: Postgrest) : UserRepository {
    override suspend fun getUserInfo(userEmail: String): UserDto {
        return withContext(Dispatchers.IO){
            postgrest.from("user").select{
                //select where signInId = userId
                filter {
                    eq("email",userEmail)
                }
            }.decodeSingle<UserDto>()
        }
    }
}