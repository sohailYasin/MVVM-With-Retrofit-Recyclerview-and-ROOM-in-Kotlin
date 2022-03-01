package com.example.samplemvvmkotlin.network.repositories

import com.example.samplemvvmkotlin.network.interfaces.RetrofitService

class CallRepository constructor(private val retrofitService: RetrofitService) {
    fun getAllCallList() = retrofitService.getAllCallList()
}