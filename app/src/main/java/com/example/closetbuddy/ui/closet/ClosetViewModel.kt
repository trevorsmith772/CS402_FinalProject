package com.example.closetbuddy.ui.closet

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This page will allow you to view your closet"
    }
    val text: LiveData<String> = _text
}