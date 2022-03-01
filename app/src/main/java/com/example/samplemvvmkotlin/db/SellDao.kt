package com.example.samplemvvmkotlin.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface SellDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(sell: Sell)


    @Delete
    suspend fun delete(sell: Sell)


    @Query("Select * from ItemToSell order by id ASC")
    fun getAllItemsToSell(): LiveData<List<Sell>>


    @Update
    suspend fun update(sell: Sell)

}