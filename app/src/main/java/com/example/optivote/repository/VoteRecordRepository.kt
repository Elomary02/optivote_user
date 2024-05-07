package com.example.optivote.repository

import com.example.optivote.model.UserDto
import com.example.optivote.model.VoteDto
import com.example.optivote.model.VoteRecordDto

interface VoteRecordRepository {
    suspend fun getVoteByCode(code: Int): VoteDto?
    suspend fun getVoteRecords(voteCode:Int):List<VoteRecordDto>?
    suspend fun submitVote(vote: decisionToSend): Boolean
    suspend fun getAllVotes():List<VoteDto>?

}