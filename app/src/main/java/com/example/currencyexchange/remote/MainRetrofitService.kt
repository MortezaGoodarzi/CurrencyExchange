package com.example.currencyexchange.remote


import com.example.currencyexchange.remote.Currency.GoldApiService
import com.example.currencyexchange.remote.Time.TimeApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object MainRetrofitService {

    private const val url = "https://tools.daneshjooyar.com/api/v1/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val timeApiService: TimeApiService = retrofit.create(TimeApiService::class.java)

    val goldApiService: GoldApiService = retrofit.create(GoldApiService::class.java)

}