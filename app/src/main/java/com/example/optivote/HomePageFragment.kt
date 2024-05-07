package com.example.optivote


import android.os.Bundle
import android.text.Layout.Directions
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.optivote.ViewModel.VoteRecordViewModel
import com.example.optivote.databinding.FragmentHomePageBinding
import com.example.optivote.model.VoteDto
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private var _binding: FragmentHomePageBinding? = null
    private val binding get() = _binding!!
    private val voteRecordViewModel: VoteRecordViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view = binding.root

        val joinBtn = binding.button
        joinBtn.setOnClickListener {
            proceedToVote()
        }

        return view
    }

    fun proceedToVote() {
        val codeEnteredByUser = binding.editText.text.toString().toIntOrNull()
        if (codeEnteredByUser != null) {
            voteRecordViewModel.getVoteByCode(codeEnteredByUser)
            voteRecordViewModel.voteLiveDate.observe(viewLifecycleOwner) { voteDto ->
                if (voteDto != null) {
                    val fragmentId = R.id.voteFragment

                    view?.findNavController()?.navigate(fragmentId, Bundle().apply {
                        putSerializable("voteData", voteDto)
                    })

                } else {
                    displayCodeMismatchDialog()
                }
            }
        } else {
            displayInvalidCodeDialog()
        }
    }



    private fun displayCodeMismatchDialog() {
        Log.d("error", "code mismatch")
    }

    private fun displayInvalidCodeDialog() {
        Log.d("error", "invalid code dialogue")
    }


}