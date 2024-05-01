package com.example.optivote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.optivote.ViewModel.SignInViewModel
import com.example.optivote.ViewModel.UserViewModel
import com.example.optivote.databinding.FragmentHistoryDetailsBinding
import com.example.optivote.databinding.FragmentPersonalAccountBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalAccountFragment : Fragment() {
    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel : SignInViewModel by viewModels()
    private val userViewModel : UserViewModel by viewModels()
    private var currentUserId : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)
        val view = binding.root


        signInViewModel.test.observe(viewLifecycleOwner) { id ->
            currentUserId = id
            Log.d("userInfo", "current user email $id")
            currentUserId?.let {
                userViewModel.getUserInfo(it)
            }
        }
        signInViewModel.getCurrentUserId()

        userViewModel.userInfoLiveDate.observe(viewLifecycleOwner){userIn->
            binding.nameTv.text = userIn.name
            binding.emailTv.text = userIn.email
            binding.phoneTv.text = userIn.phone
            binding.passwordTv.text = userIn.password

        }


        return view

    }


}