package com.example.closetbuddy.ui.today

import androidx.lifecycle.*
import com.example.closetbuddy.models.Clothing
import com.example.closetbuddy.repository.ClothesRepository
import com.example.closetbuddy.utils.CalendarHelper

class TodayViewModel(private val clothesRepository: ClothesRepository) : ViewModel() {

    private val season = CalendarHelper.getSeason()
    private val _order = MutableLiveData("id")
    val clothes: LiveData<List<Clothing>> = Transformations.switchMap(_order) {
        clothesRepository.getClothesBySeason("%${season.name}%", it).asLiveData()
    }

    fun setOrder(sort: String) {
        _order.postValue(sort)
    }

    companion object {
        fun provideFactory(
            clothesRepository: ClothesRepository
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return TodayViewModel(clothesRepository) as T
            }
        }
    }

}