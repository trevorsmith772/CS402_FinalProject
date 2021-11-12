package com.example.closetbuddy.ui.additem

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This page will allow you to add clothing items"
    }
    val text: LiveData<String> = _text
}