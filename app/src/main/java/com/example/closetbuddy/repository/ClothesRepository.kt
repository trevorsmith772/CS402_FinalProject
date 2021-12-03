package com.example.closetbuddy.repository

import com.example.closetbuddy.models.Clothing
import com.example.closetbuddy.room.ClothesDao

class ClothesRepository(private val clothesDao: ClothesDao) {

    fun getClothing() = clothesDao.getClothes()

    fun getClothing(order: String) = clothesDao.getClothes(order)

    fun searchClothing(query: String) = clothesDao.searchClothes(query)

    //fun getClothesBySeason(season: String) = clothesDao.getClothesBySeason(season)

    //fun getClothesBySeason(season: String, order: String) = clothesDao.getClothesBySeason(season, order)

    suspend fun createClothing(clothing: Clothing) {
        clothesDao.addClothing(clothing)
    }

    suspend fun updateClothing(clothing: Clothing) {
        clothesDao.updateClothing(clothing)
    }

    suspend fun removeClothing(clothing: Clothing) {
        clothesDao.removeClothing(clothing)
    }
}