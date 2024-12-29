package com.example.currencyexchange.remote.Model

data class GoldModel(
    val message: String,
    val success: Int,
    val data: AllData
)

data class AllData(
    val golds: ArrayList<ContentModel>,
    val currencies: ArrayList<ContentModel>,
    val cryptocurrencies: ArrayList<ContentModel>
)

data class ContentModel(
    val label: String,
    val price: Int
)