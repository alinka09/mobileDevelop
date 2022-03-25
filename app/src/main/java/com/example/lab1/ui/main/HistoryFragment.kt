package com.example.lab1.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab1.ActivityCallback
import com.example.lab1.R
import com.example.lab1.databinding.FragmentHistoryBinding

class HistoryFragment : Fragment() {

    private lateinit var binding: FragmentHistoryBinding

    companion object {
        fun newInstance() = HistoryFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentHistoryBinding.inflate(layoutInflater)

        binding.filterButton.setOnClickListener {
            val activityCallback = requireActivity() as ActivityCallback
            activityCallback.showHistoryFilterView()
        }

        return binding.root
    }
}