package com.example.optivote


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RadioButton
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.optivote.ViewModel.UserViewModel
import com.example.optivote.ViewModel.VoteRecordViewModel
import com.example.optivote.databinding.FragmentVoteBinding
import com.example.optivote.model.UserDto
import com.example.optivote.model.UserInInfo
import com.example.optivote.model.VoteDto
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VoteFragment : Fragment() {
    private var _binding: FragmentVoteBinding? = null
    private val binding get() = _binding!!
    private val voteRecordViewModel: VoteRecordViewModel by viewModels()
    private val userViewModel: UserViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentVoteBinding.inflate(inflater, container, false)
        val view = binding.root

        val titleTv = binding.voteTitle
        val contentTv = binding.voteContent
        val radioGroup = binding.radioGroup
        val submitBtn = binding.submitBtn

        val voteData = arguments?.getSerializable("voteData") as? VoteDto
        if (voteData != null) {
            Log.d("idVote","${voteData.idVote}")
            titleTv.text = voteData.title
            contentTv.text = voteData.content
        }

        val user = UserDto(
            id = UserInInfo.id,
            name = UserInInfo.name,
            email = UserInInfo.email,
            phone = UserInInfo.phone,
            password = UserInInfo.password,
            image = UserInInfo.image,
            signInId = UserInInfo.signInId
        )
        submitBtn.setOnClickListener {
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            val selectedRadioButton = view.findViewById<RadioButton>(selectedRadioButtonId)
            val decision = selectedRadioButton?.text?.toString()
            if (decision != null && voteData != null) {
                voteData.idVote?.let { it1 ->
                    user.id?.let { it2 ->
                        voteRecordViewModel.submitVote(decision, it2,
                            it1
                        )
                    }
                }
            }
            Log.d("tag", "decision $decision")

        }



        return view
    }

}
