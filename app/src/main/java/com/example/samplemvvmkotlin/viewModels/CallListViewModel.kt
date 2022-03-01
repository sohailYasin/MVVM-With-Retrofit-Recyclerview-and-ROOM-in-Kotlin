package com.example.samplemvvmkotlin.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.samplemvvmkotlin.models.CallListModel
import com.example.samplemvvmkotlin.network.repositories.CallRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CallListViewModel constructor(private val repository: CallRepository) : ViewModel() {

    val movieList = MutableLiveData<List<CallListModel>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCalls() {
        val response = repository.getAllCallList()
        response.enqueue(object : Callback<List<CallListModel>> {
            override fun onResponse(
                call: Call<List<CallListModel>>,
                response: Response<List<CallListModel>>
            ) {
                movieList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<CallListModel>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}