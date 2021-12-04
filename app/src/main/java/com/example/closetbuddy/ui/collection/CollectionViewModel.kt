package com.example.closetbuddy.ui.collection

import androidx.lifecycle.*
import com.example.closetbuddy.models.Clothing
import com.example.closetbuddy.repository.ClothesRepository

class CollectionViewModel(private val clothesRepository: ClothesRepository) : ViewModel() {

    private val _order = MutableLiveData("id")
    var clothes: LiveData<List<Clothing>> = Transformations.switchMap(_order) {
        clothesRepository.getClothing(it).asLiveData()
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
                return CollectionViewModel(clothesRepository) as T
            }
        }
    }

}