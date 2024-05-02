package com.example.optivote

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.optivote.ViewModel.SignInViewModel
import com.example.optivote.ViewModel.UserViewModel
import com.example.optivote.databinding.FragmentPersonalAccountBinding
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PersonalAccountFragment : Fragment() {
    private var _binding: FragmentPersonalAccountBinding? = null
    private val binding get() = _binding!!
    private val signInViewModel : SignInViewModel by viewModels()
    private val userViewModel : UserViewModel by viewModels()
    private var currentUserEmail : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPersonalAccountBinding.inflate(inflater, container, false)
        val view = binding.root


        signInViewModel.test.observe(viewLifecycleOwner) { email ->
            currentUserEmail = email
            Log.d("userInfo", "current user email $email")
            currentUserEmail?.let {
                userViewModel.getUserInfo(it)
            }
        }
        signInViewModel.getCurrentUserEmail()

        userViewModel.userInfoLiveDate.observe(viewLifecycleOwner){userIn->
            binding.nameTv.text = userIn.name
            binding.emailTv.text = userIn.email
            binding.phoneTv.text = userIn.phone
            binding.passwordTv.text = userIn.password

            val imgUrl = buildImageUrl(userIn.image)

            Picasso.get().load(imgUrl).into(binding.profileImageView)
            Log.d("image url",imgUrl)

        }


        return view

    }
    private fun buildImageUrl(imageUrl: String): String {
        return imageUrl.replace("http://127.0.0.1", "http://192.168.1.2")
    }


}