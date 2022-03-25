package com.example.lab1.viewmodel

import android.content.Context
import com.example.lab1.data.CurrencyApi
import com.example.lab1.data.CurrencyRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RepositoryInitializer{

    private var api: CurrencyApi? = null
    private lateinit var currencyRepository: CurrencyRepository

    fun getRepository(): CurrencyRepository {
        if (api == null) {

            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BASIC)
            val client: OkHttpClient = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            val retrofit = Retrofit.Builder()
                .client(client)
                .baseUrl("http://data.fixer.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            api = retrofit.create(CurrencyApi::class.java)
        }

        if (api != null)
            currencyRepository = CurrencyRepository(api!!)
        return currencyRepository
    }
}