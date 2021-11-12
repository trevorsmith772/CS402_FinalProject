package com.example.closetbuddy.ui.outfits

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is the page that will display your different outfits"
    }
    val text: LiveData<String> = _text
}