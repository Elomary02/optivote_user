package com.example.optivote.repository

import com.example.optivote.model.UserDto

interface UserRepository {
    suspend fun getUserInfo(userId:String):UserDto
}