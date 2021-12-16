package com.example.closetbuddy.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.closetbuddy.models.Clothing

@TypeConverters(value = [com.example.closetbuddy.room.TypeConverters::class])
@Database(entities = [Clothing::class], version = 1)
abstract class CBDatabase : RoomDatabase() {

    abstract fun clothesDao(): ClothesDao

    companion object {
        @Volatile
        private var INSTANCE: CBDatabase? = null

        fun getInstance(context: Context): CBDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CBDatabase::class.java,
                    "CBDatabase"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}