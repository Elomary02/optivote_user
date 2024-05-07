package com.example.optivote.model

import kotlinx.serialization.Serializable

@Serializable
data class UserDto (val id : Int? = null,
               val name:String? = null,
               val email:String? = null,
               val phone:String? = null,
               val password:String? = null,
               val image:String? = null,
               val signInId:String? = null
)