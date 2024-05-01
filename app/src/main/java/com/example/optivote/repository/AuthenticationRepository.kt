package com.example.optivote.repository



interface AuthenticationRepository {

    suspend fun signIn(email:String, password:String):Boolean
    suspend fun getCurrentUserId():String
}