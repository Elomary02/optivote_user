package com.example.optivote.repository

import com.example.optivote.model.VoteDto
import com.example.optivote.model.VoteRecordDto
import com.example.optivote.model.decisionToSend

interface VoteRecordRepository {
    suspend fun getVoteByCode(code: Int): VoteDto?
    suspend fun getVoteRecords(voteCode:Int):List<VoteRecordDto>?
    suspend fun submitVote(vote: decisionToSend): Boolean
}