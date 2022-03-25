package com.example.lab1.ui.main

import android.os.Binder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.lab1.ActivityCallback
import com.example.lab1.R
import com.example.lab1.databinding.FragmentHistoryFilterBinding

class FilterFragment : Fragment() {

    private lateinit var binding: FragmentHistoryFilterBinding

    companion object {
        fun newInstance() = FilterFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHistoryFilterBinding.inflate(layoutInflater)

        binding.applyButton.setOnClickListener {
            val activityCallback = requireActivity() as ActivityCallback
            activityCallback.showHistoryView()
        }



        return binding.root
    }
}