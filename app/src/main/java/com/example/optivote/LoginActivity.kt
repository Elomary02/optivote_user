package com.example.optivote

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.optivote.ViewModel.SignInViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject


@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private lateinit var loginBtn: Button
    private lateinit var EmailEt : EditText
    private lateinit var PasswordEt:EditText
    private val viewModel:SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        loginBtn = findViewById(R.id.btnLogin)

        EmailEt = findViewById(R.id.loginEt)
        PasswordEt = findViewById(R.id.passwordEt)


        loginBtn.setOnClickListener{

                val myEmail = EmailEt.text.toString()
                val myPassword = PasswordEt.text.toString()
                viewModel.onSignIn(myEmail,myPassword)




        }
        observeSignInState()


    }
    private fun observeSignInState(){
        lifecycleScope.launchWhenStarted {
            viewModel.signInState.collect{state->
                when(state){
                    is SignInViewModel.SignInState.Success->{
                        if (state.success){
                            Log.d("LoginActivity", "Sign-In success")
                            val intent = Intent(this@LoginActivity,MainActivity::class.java)
                            startActivity(intent)
                            viewModel.test.observe(this@LoginActivity, Observer { email->
                                Log.d("userInfo","current user email $email")
                            })
                            viewModel.getCurrentUserEmail()
                        }else{
                            Log.d("LoginActivity", "Sign-in failed")
                        }
                    }
                    is SignInViewModel.SignInState.Error->{
                        Log.d("LoginActivity", "Sign-in error: ${state.message}")
                    }
                    SignInViewModel.SignInState.Loading->{

                    }
                    SignInViewModel.SignInState.Initial->{

                    }
                }
            }
        }
    }

}
