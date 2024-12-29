package com.example.currencyexchange.remote.Currency

import com.example.currencyexchange.remote.MainRetrofitService
import com.example.currencyexchange.remote.Model.GoldModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GoldApiRepository private constructor() {

    companion object {

        private var GoldApiRepository: GoldApiRepository? = null

        val Instance: GoldApiRepository
            get() {
                if (GoldApiRepository == null) GoldApiRepository = GoldApiRepository()
                return GoldApiRepository!!
            }
    }

    fun getGold(request: GoldRequest) {

        MainRetrofitService.goldApiService.getGold().enqueue(
            object : Callback<GoldModel> {
                override fun onResponse(call: Call<GoldModel>, response: Response<GoldModel>) {

                    if (response.isSuccessful){
                        val data = response.body() as GoldModel
                        request.onSuccess(data)
                    } else {
                        request.onNotSuccess("not success")
                    }

                }

                override fun onFailure(call: Call<GoldModel>, t: Throwable) {
                    request.onError(t.message.toString())
                }
            }
        )
    }
}