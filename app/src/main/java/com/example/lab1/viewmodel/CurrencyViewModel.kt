package com.example.lab1.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab1.data.CurrencyResponse
import com.example.lab1.data.CurrencyRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import androidx.lifecycle.viewModelScope

class CurrencyViewModel(private val currencyRepository: CurrencyRepository): ViewModel() {

    val liveData = MutableLiveData<CurrencyResponse>()

    fun getCurrencies() = viewModelScope.launch(Dispatchers.IO) {
        liveData.postValue(currencyRepository.getCurrencies())
    }

    fun getData(): LiveData<CurrencyResponse>{
        return liveData
    }
}