package com.example.currencyexchange.remote.Currency

import com.example.currencyexchange.remote.Model.GoldModel
import retrofit2.Call
import retrofit2.http.GET

interface GoldApiService {

    @GET("currencies")
    fun getGold(): Call<GoldModel>
}