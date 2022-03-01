package com.example.samplemvvmkotlin.network.interfaces

import com.example.samplemvvmkotlin.models.BuyModel
import com.example.samplemvvmkotlin.models.CallListModel
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface RetrofitService {
    @GET("call")
    fun getAllCallList(): Call<List<CallListModel>>

    @GET("buy")
    fun getAllBuyList(): Call<List<BuyModel>>

    companion object {
        var retrofitService: RetrofitService? = null

        fun getInstance(): RetrofitService {
            if (retrofitService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl("https://my-json-server.typicode.com/imkhan334/demo-1/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                retrofitService = retrofit.create(RetrofitService::class.java)
            }
            return retrofitService!!
        }
    }
}