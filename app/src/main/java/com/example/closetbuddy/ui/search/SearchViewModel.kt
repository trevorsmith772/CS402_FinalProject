package com.example.closetbuddy.ui.search

import androidx.lifecycle.*
import com.example.closetbuddy.models.Clothing
import com.example.closetbuddy.repository.ClothesRepository

class SearchViewModel(private val clothesRepository: ClothesRepository) : ViewModel() {

    var clothes: LiveData<List<Clothing>>
    private val _query = MutableLiveData("")

    init {
        clothes = Transformations.switchMap(_query) {
            if (it.isEmpty()) {
                clothesRepository.getClothing().asLiveData()
            } else {
                clothesRepository.searchClothing("%$it%").asLiveData()
            }
        }
    }

    fun search(query: String) {
        _query.value = query
    }

    companion object {
        fun provideFactory(
                clothesRepository: ClothesRepository
        ) : ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                return SearchViewModel(clothesRepository) as T
            }
        }
    }
}