package com.example.optivote


import android.os.Build
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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.optivote.ViewModel.VoteRecordViewModel
import com.example.optivote.adapters.RecentVotesAdapter
import com.example.optivote.databinding.FragmentHomePageBinding
import com.example.optivote.model.VoteDto
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomePageFragment : Fragment() {
    private var _binding: FragmentHomePageBinding? = null

    private val voteRecordViewModel: VoteRecordViewModel by viewModels()
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomePageBinding.inflate(inflater, container, false)
        val view = binding.root
        var recentVotesRecyclerView : RecyclerView = binding.recentVotesRecyclerView
        var adapter : RecentVotesAdapter = RecentVotesAdapter(findNavController())
        recentVotesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        recentVotesRecyclerView.adapter = adapter


        val joinBtn = binding.button
        joinBtn.setOnClickListener {
            proceedToVote()
        }

        voteRecordViewModel.recentVotesLiveData.observe(viewLifecycleOwner){
            recentVotesData-> adapter.submitList(recentVotesData)

            Log.d("recentVotes","$recentVotesData")
        }
        voteRecordViewModel.getRecentVote()

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