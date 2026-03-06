package com.example.listfromapi.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [FavouriteEntity:: class], version = 1 )
abstract class DataBase: RoomDatabase() {
    abstract fun funDao(): DAO

    companion object{
        @Volatile
        private var Instance: DataBase? = null

        fun getDatabase(context: Context): DataBase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, DataBase::class.java, "todo_db")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}