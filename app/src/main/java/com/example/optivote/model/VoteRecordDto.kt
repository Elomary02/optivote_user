package com.example.optivote.model

import kotlinx.serialization.Serializable

@Serializable
data class VoteRecordDto (val idDecision: Long? = null,
                          val decision: String? = null,
                          val userIdFk: Long? = null,
                          val user: UserDto? = null,
                          val voteIdFk: Long? = null):java.io.Serializable