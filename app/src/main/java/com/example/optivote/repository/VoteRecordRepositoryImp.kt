package com.example.optivote.repository

import android.util.Log
import com.example.optivote.model.UserDto
import com.example.optivote.model.VoteDto
import com.example.optivote.model.VoteRecordDto
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.query.Columns
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class VoteRecordRepositoryImp @Inject constructor(private val auth: Auth, private val postgrest: Postgrest) : VoteRecordRepository {
    override suspend fun getVoteByCode(code: Int): VoteDto? {
        return try {
            withContext(Dispatchers.IO) {
                val vote = postgrest.from("vote").select(columns = Columns.list("idVote, content, title")) {
                    filter {
                        eq("code", code)
                    }
                }.decodeSingleOrNull<VoteDto>()
                vote
            }
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getVoteRecords(voteCode:Int): List<VoteRecordDto>? {
        return try {
            withContext(Dispatchers.IO){
                val voteRecords = postgrest.from("decision").select(Columns.raw("idDecision, decision, vote!inner(code),user(id,name,image)")){
                    filter {
                        eq("vote.code",voteCode)
                    }
                }.decodeList<VoteRecordDto>()
                voteRecords
            }
        }catch (e:Exception){
            null
        }
    }

    override suspend fun getAllVotes(): List<VoteDto>? {
        return try {
            withContext(Dispatchers.IO){
                val allVotes = postgrest.from("vote").select(Columns.raw("code, title, date,status")).decodeList<VoteDto>()
                allVotes
            }
        }catch (e:Exception){
            null
        }
    }
    }

    override suspend fun submitVote(voteRecord: decisionToSend): Boolean {
        return try {
            withContext(Dispatchers.IO) {
                postgrest.from("decision").insert(voteRecord)
                true
            }
            true
        } catch (e: java.lang.Exception) {
            throw e
        }
    }

}