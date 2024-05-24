package com.example.optivote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.optivote.ViewModel.SignInViewModel
import com.example.optivote.databinding.FragmentChangePasswordBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChangePasswordFragment : Fragment() {
    private var _binding: FragmentChangePasswordBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel : SignInViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChangePasswordBinding.inflate(inflater, container, false)
        val view = binding.root

        val newPasswordTf = binding.newPassword
        val newPasswordConfirmedTf = binding.newPasswordConfirmed
        val updateBtn = binding.buttonChangePassword

        updateBtn.setOnClickListener {
            val newPassword = newPasswordTf.text.toString()
            val newPasswordConfirmed = newPasswordConfirmedTf.text.toString()

            if (newPassword == newPasswordConfirmed) {
                signInViewModel.updatePassword(newPassword)
                Log.d("update success", "matched passwords")
            } else {
                Log.d("unmatched", "unmatched passwords")
            }
        }

        return  view
    }


}