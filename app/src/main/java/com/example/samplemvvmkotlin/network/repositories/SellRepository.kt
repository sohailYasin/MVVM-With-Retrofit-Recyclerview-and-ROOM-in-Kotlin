package com.example.samplemvvmkotlin.network.repositories

import androidx.lifecycle.LiveData
import com.example.samplemvvmkotlin.db.Sell
import com.example.samplemvvmkotlin.db.SellDao

class SellRepository(private val sellDao: SellDao) {

    // on below line we are creating a variable for our list
    // and we are getting all the notes from our DAO class.
    val allNotes: LiveData<List<Sell>> = sellDao.getAllItemsToSell()

    // on below line we are creating an insert method
    // for adding the note to our database.
    suspend fun insert(sell: Sell) {
        sellDao.insert(sell)
    }

    // on below line we are creating a delete method
    // for deleting our note from database.
    suspend fun delete(sell: Sell) {
        sellDao.delete(sell)
    }

    // on below line we are creating a update method for
    // updating our note from database.
    suspend fun update(sell: Sell) {
        sellDao.update(sell)
    }
}