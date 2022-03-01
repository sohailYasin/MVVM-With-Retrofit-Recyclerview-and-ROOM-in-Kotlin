package com.example.samplemvvmkotlin.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(Sell::class), version = 1, exportSchema = false)
abstract class SellDatabase : RoomDatabase() {

    abstract fun getSellItemDao(): SellDao

    companion object {
        // Singleton prevents multiple
        // instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: SellDatabase? = null

        fun getDatabase(context: Context): SellDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SellDatabase::class.java,
                    "sell_database"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}