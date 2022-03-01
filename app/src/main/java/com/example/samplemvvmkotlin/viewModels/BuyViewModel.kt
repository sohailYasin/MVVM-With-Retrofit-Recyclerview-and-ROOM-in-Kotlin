package com.example.samplemvvmkotlin.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvmkotlin.models.BuyModel
import com.example.samplemvvmkotlin.network.repositories.BuyRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BuyViewModel constructor(private val repository: BuyRepository) : ViewModel() {

    val buyList = MutableLiveData<List<BuyModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllBuyList() {
        val response = repository.getAllBuyList()
        response.enqueue(object : Callback<List<BuyModel>> {
            override fun onResponse(
                call: Call<List<BuyModel>>,
                response: Response<List<BuyModel>>
            ) {
                buyList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<BuyModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}