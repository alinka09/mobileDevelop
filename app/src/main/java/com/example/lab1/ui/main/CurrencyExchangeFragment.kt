package com.example.lab1.ui.main

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.addTextChangedListener
import com.example.lab1.ActivityCallback
import com.example.lab1.R
import com.example.lab1.databinding.FragmentCurrencyExchangeBinding
import kotlin.math.floor
import kotlin.math.roundToInt


class CurrencyExchangeFragment : Fragment() {

    companion object {
        fun newInstance() = CurrencyExchangeFragment()
    }

    private lateinit var binding: FragmentCurrencyExchangeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        binding = FragmentCurrencyExchangeBinding.inflate(layoutInflater)
        binding.exchangeButton.setOnClickListener {
            val activityCallback = requireActivity() as ActivityCallback
            activityCallback.showCurrencyListView()
        }
        return binding.root
    }
}