package com.example.optivote

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.optivote.databinding.FragmentHistoryDetailsBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetBehavior.STATE_COLLAPSED

class HistoryDetails : Fragment() {
    private var _binding: FragmentHistoryDetailsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryDetailsBinding.inflate(inflater, container, false)
        val view = binding.root

        BottomSheetBehavior.from(binding.sheets).apply {
            peekHeight = 50
            this.state = STATE_COLLAPSED
        }
        binding.sheets.elevation = resources.getDimension(R.dimen.bottom_sheet_elevation)
        return view


    }


}