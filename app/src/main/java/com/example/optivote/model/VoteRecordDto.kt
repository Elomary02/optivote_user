package com.example.optivote.model

import kotlinx.serialization.Serializable

@Serializable
data class VoteRecordDto (val recordId: Int,
                     val vote: VoteDto,
                     val recordDectionnary: Map<UserDto, String>)