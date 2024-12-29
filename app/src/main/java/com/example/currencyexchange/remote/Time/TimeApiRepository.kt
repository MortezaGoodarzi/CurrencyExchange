package com.example.currencyexchange.remote.Time

import com.example.currencyexchange.remote.MainRetrofitService
import com.example.currencyexchange.remote.Model.DateModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TimeApiRepository private constructor() {

    companion object {

        private var timeApiRepository: TimeApiRepository? = null

        val Instance: TimeApiRepository
            get() {
                if (timeApiRepository == null) timeApiRepository = TimeApiRepository()
                return timeApiRepository!!
            }
    }

    fun getDate(request: TimeRequest) {

        MainRetrofitService.timeApiService.getTime(true).enqueue(
            object : Callback<DateModel> {
                override fun onResponse(call: Call<DateModel>, response: Response<DateModel>) {

                    if (response.isSuccessful){
                        val data = response.body() as DateModel
                        request.onSuccess(data)
                    } else {
                        request.onNotSuccess("not success")
                    }

                }

                override fun onFailure(call: Call<DateModel>, t: Throwable) {
                    request.onError(t.message.toString())
                }
            }
        )
    }
}