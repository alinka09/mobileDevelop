package com.example.lab1.ui.main

import android.content.Context
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.lab1.data.CurrencyData
import com.example.lab1.databinding.MainFragmentBinding
import com.example.lab1.viewmodel.CurrencyViewModel
import com.example.lab1.viewmodel.CurrencyViewModelFactory
import com.example.lab1.viewmodel.RepositoryInitializer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.example.lab1.ActivityCallback
import com.example.lab1.MainActivity
import com.example.lab1.data.CurrencyResponse

interface ItemClickListener{
    fun showCurrencyExchangeView(firstCurrency: CurrencyData)
}

class MainFragment() : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: MainFragmentBinding
    private lateinit var adapter: CurrencyAdapter
    private lateinit var viewModelFactory: CurrencyViewModelFactory
    private lateinit var liveData: LiveData<CurrencyResponse>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater)


        viewModelFactory = CurrencyViewModelFactory(RepositoryInitializer.getRepository())
        val currencyViewModel = ViewModelProvider(this, viewModelFactory)
            .get(CurrencyViewModel::class.java)

        liveData = currencyViewModel.getData()
        currencyViewModel.getCurrencies()

        adapter = CurrencyAdapter(object : ItemClickListener{
            override fun showCurrencyExchangeView(firstCurrency: CurrencyData) {

                var secondCurrency: CurrencyData? = null

                liveData.value?.let {
                    for (currency in it.rates){
                        if (currency.key == "RUB")
                            secondCurrency = CurrencyData(name = currency.key,
                                exchangeRate = currency.value,
                                favorite = false)
                    }
                }

                secondCurrency?.let {
                    val activityCallback = requireActivity() as ActivityCallback
                    activityCallback.showCurrencyExchangeView(firstCurrency, it)
                }
            }
        })
        val layoutManager =  LinearLayoutManager(requireContext())
        binding.recycleview.layoutManager = layoutManager
        binding.recycleview.adapter = adapter

        liveData.observe(requireActivity(), Observer {
            liveData.value?.let {
                showCurrencies(it.rates)
            }
        })

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    fun showCurrencies(currencies: Map<String,Float>) {
        val currencisList = mutableListOf<CurrencyData>()
        for (currency in currencies) {
            currencisList.add(CurrencyData(currency.key, currency.value, false))
        }
        adapter.set(currencisList)
    }

}