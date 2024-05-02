package com.example.optivote

import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
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

        }

        binding.logoutBtn.setOnClickListener {
            showLogoutConfirmationDialog()
        }
        return view



    }


    private fun showLogoutConfirmationDialog() {
        val alertDialog = AlertDialog.Builder(requireContext(), R.style.CustomAlertDialog)
            .setTitle("تأكيد الخروج")
            .setMessage("هل أنت متأكد أنك تريد تسجيل الخروج؟")
            .setPositiveButton("تسجيل خروج", null)
            .setNegativeButton("إلغاء", null)
            .create()

        alertDialog.setOnShowListener {
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.parseColor("#214372"))
            alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE).setTextColor(Color.parseColor("#214372"))
            alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener {
                // Handle positive button click
                val intent = Intent(activity, LoginActivity::class.java)
                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(intent)
                activity?.finish()
            }
        }

        alertDialog.window?.setBackgroundDrawableResource(R.drawable.dialog_background)
        alertDialog.show()
    }











}