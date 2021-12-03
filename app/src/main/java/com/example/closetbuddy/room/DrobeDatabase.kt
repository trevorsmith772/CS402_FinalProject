package com.example.closetbuddy.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.closetbuddy.models.Clothing

//@TypeConverters(value = [com.example.closetbuddy.room.TypeConverters::class])
@Database(entities = [Clothing::class], version = 1)
abstract class DrobeDatabase : RoomDatabase() {

    abstract fun clothesDao(): ClothesDao

    companion object {
        @Volatile
        private var INSTANCE: DrobeDatabase? = null

        fun getInstance(context: Context): DrobeDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DrobeDatabase::class.java,
                    "DrobeDatabase"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}