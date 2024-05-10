package com.example.optivote.repository

import com.example.optivote.model.VoteDto
import kotlinx.coroutines.flow.Flow

interface WaitingPageRepository {
    suspend fun getVoteByCode(code:Int):Flow<VoteDto>
}