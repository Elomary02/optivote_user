package com.example.optivote.ViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.optivote.repository.AuthenticationRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SignInViewModel @Inject constructor( private val authenticationRepository: AuthenticationRepository):ViewModel() {

    private val _test = MutableLiveData<String>()
    val test : LiveData<String> = _test

    private val _signInState = MutableStateFlow<SignInState>(SignInState.Initial)
    val signInState: StateFlow<SignInState> = _signInState
    sealed class SignInState {
        object Initial : SignInState()
        object Loading : SignInState()
        data class Success(val success: Boolean) : SignInState()
        data class Error(val message: String) : SignInState()
    }

    fun onSignIn(emilTest:String,passwordTest:String){
        viewModelScope.launch { _signInState.value = SignInState.Loading
                try {
                    val success = authenticationRepository.signIn(emilTest, passwordTest)
                    _signInState.value = SignInState.Success(success)
                }catch (e:Exception){
                    _signInState.value = SignInState.Error("Login Error")
                }
        }
    }
    fun getCurrentUserEmail(){
        viewModelScope.launch {
            val fetchedResult = authenticationRepository.getCurrentUserEmail()
            _test.postValue(fetchedResult)
        }
    }
}