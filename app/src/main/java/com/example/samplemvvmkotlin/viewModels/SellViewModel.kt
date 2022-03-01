package com.example.samplemvvmkotlin.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.samplemvvmkotlin.db.Sell
import com.example.samplemvvmkotlin.db.SellDatabase
import com.example.samplemvvmkotlin.network.repositories.SellRepository
import kotlinx.coroutines.Dispatchers
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class SellViewModel(application: Application) : AndroidViewModel(application) {

    // on below line we are creating a variable
    // for our all notes list and repository
    val allSellItems: LiveData<List<Sell>>
    val repository: SellRepository

    // on below line we are initializing
    // our dao, repository and all notes
    init {
        val dao = SellDatabase.getDatabase(application).getSellItemDao()
        repository = SellRepository(dao)
        allSellItems = repository.allNotes
    }

    // on below line we are creating a new method for deleting a note. In this we are
    // calling a delete method from our repository to delete our note.
    fun deleteNote(sell: Sell) = viewModelScope.launch(Dispatchers.IO) {
        repository.delete(sell)
    }

    // on below line we are creating a new method for updating a note. In this we are
    // calling a update method from our repository to update our note.
    fun updateNote(sell: Sell) = viewModelScope.launch(Dispatchers.IO) {
        repository.update(sell)
    }


    // on below line we are creating a new method for adding a new note to our database
    // we are calling a method from our repository to add a new note.
    fun addNote(sell: Sell) = viewModelScope.launch(Dispatchers.IO) {
        repository.insert(sell)
    }
}