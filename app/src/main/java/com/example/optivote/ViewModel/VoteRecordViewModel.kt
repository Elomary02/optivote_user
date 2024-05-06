package com.example.optivote.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optivote.model.VoteDto
import com.example.optivote.model.VoteRecordDto
import com.example.optivote.repository.VoteRecordRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VoteRecordViewModel @Inject constructor(private val voteRecordRepository: VoteRecordRepository): ViewModel() {
    private val _voteLiveDate = MutableLiveData<VoteDto?>()
    val voteLiveDate : MutableLiveData<VoteDto?> = _voteLiveDate

    private val _voteRecordsLiveDate = MutableLiveData<List<VoteRecordDto?>?>()
    val voteRecordsLiveDate : MutableLiveData<List<VoteRecordDto?>?> = _voteRecordsLiveDate

    private val _agreedCountLiveData = MutableLiveData<Int>()
    private val _disagreedCountLiveData = MutableLiveData<Int>()
    private val _neutralCountLiveData = MutableLiveData<Int>()

    val agreedCountLiveData: LiveData<Int> = _agreedCountLiveData
    val disagreedCountLiveData: LiveData<Int> = _disagreedCountLiveData
    val neutralCountLiveData: LiveData<Int> = _neutralCountLiveData


    private val _totalCountLiveData = MutableLiveData<Int>()
    val totalCountLiveData: LiveData<Int> = _totalCountLiveData

    /*private val _currentUserDecisionLiveData = MutableLiveData<VoteRecordDto?>()
    val currentUserDecisionLiveData: MutableLiveData<VoteRecordDto?> = _currentUserDecisionLiveData*/






    fun getVoteByCode(code: Int) {
        viewModelScope.launch {
            val result = voteRecordRepository.getVoteByCode(code)
            _voteLiveDate.postValue(result)
        }
    }

    fun getVotRecords(code:Int){
        viewModelScope.launch {
            val result = voteRecordRepository.getVoteRecords(code)
            _voteRecordsLiveDate.postValue(result)
            updateCounts(result)
        }

    }
    private fun updateCounts(voteData :List<VoteRecordDto>?){
        var agreed = 0
        var disagreed = 0
        var neutral = 0
        var total = 0
        voteData?.forEach {
            data->
            when(data.decision){
                "موافق" -> agreed++
                "رافض" -> disagreed++
                else -> neutral++
            }
        }
        total = agreed+disagreed+neutral
        _agreedCountLiveData.postValue(agreed)
        _disagreedCountLiveData.postValue(disagreed)
        _neutralCountLiveData.postValue(neutral)
        _totalCountLiveData.postValue(total)
    }

    /*fun currentUserDecision(voteData: List<VoteRecordDto?>?, currentUserId: Int){
        voteData?.forEach { data->
            if (data != null) {
                if(data.user?.id==currentUserId){
                    _currentUserDecisionLiveData.postValue(data)
                }
            }
        }
    }*/



}