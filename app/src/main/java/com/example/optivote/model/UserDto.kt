package com.example.optivote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (val id : Int,
               val name:String,
               val email:String,
               val phone:String,
               val password:String,
               val image:Int,
               val signInId:String
)