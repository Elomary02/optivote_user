package com.example.optivote.model

import kotlinx.serialization.Serializable

@Serializable
class VoteRecordDto (val recordId: Int,
                     val vote: VoteDto,
                     val recordDectionnary: Map<UserDto, String>)