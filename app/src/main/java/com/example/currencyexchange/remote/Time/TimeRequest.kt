package com.example.currencyexchange.remote.Time

import com.example.currencyexchange.remote.Model.DateModel


interface TimeRequest {

    fun onSuccess(data : DateModel)

    fun onNotSuccess(message : String)

    fun onError(error: String)
}