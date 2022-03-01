package com.example.samplemvvmkotlin.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//ItemToSell
@Entity(tableName = "ItemToSell")
class Sell(
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "quantity") val quantity: String,
    @ColumnInfo(name = "type") val type: String
) {

    @PrimaryKey(autoGenerate = true)
    var id = 0
}