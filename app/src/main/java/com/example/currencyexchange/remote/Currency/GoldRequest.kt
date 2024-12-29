package com.example.currencyexchange.remote.Currency


import com.example.currencyexchange.remote.Model.GoldModel


interface GoldRequest {

    fun onSuccess(data : GoldModel)

    fun onNotSuccess(message : String)

    fun onError(error: String)
}