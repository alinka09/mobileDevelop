package com.example.lab1.data

import com.example.lab1.data.CurrencyApi
import com.example.lab1.data.CurrencyResponse
import androidx.lifecycle.LiveData

import androidx.lifecycle.MutableLiveData
import java.util.*


class CurrencyRepository(private val currencyApi: CurrencyApi) {

    suspend fun getCurrencies(): CurrencyResponse{
        return currencyApi.getCurrencies()
    }
}