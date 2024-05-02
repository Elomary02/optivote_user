package com.example.optivote.model

import kotlinx.datetime.*

import kotlinx.serialization.Serializable

@Serializable
data class VoteDto (val voteId: Int,
                    val joinCode: Int,
                    val title: String,
                    val content: String,
                    val date: LocalDate,
                    val startTime: LocalTime,
                    val endTime: LocalTime,
                    val statue: String,
                    val listVoters: List<UserDto>,
                    val session: SessionDto)