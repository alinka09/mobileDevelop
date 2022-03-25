package com.example.lab1.data

import retrofit2.http.GET

interface CurrencyApi {
    @GET("/api/latest?access_key=a70c497e9c98eedc695b9a4add7cdd05")
    suspend fun getCurrencies(): CurrencyResponse

    @GET("/api/convert")
    suspend fun convert()
}
