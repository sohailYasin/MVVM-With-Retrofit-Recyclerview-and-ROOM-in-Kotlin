package com.example.samplemvvmkotlin.network.repositories

import com.example.samplemvvmkotlin.network.interfaces.RetrofitService

class BuyRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllBuyList() = retrofitService.getAllBuyList()
}