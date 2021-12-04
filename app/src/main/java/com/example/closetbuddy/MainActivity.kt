package com.example.closetbuddy

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import com.example.closetbuddy.databinding.ActivityMainBinding
import com.example.closetbuddy.models.Clothing
import com.example.closetbuddy.models.ClothingViewModel
import com.example.closetbuddy.ui.details.DetailsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val clothingViewModel: ClothingViewModel by viewModels {
        ClothingViewModel.provideFactory(MyApplication.clothesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

    }

    fun openDetails(item: Clothing?) {
        clothingViewModel.selectItem(item)
        val newFragment = DetailsFragment()
        newFragment.show(supportFragmentManager, newFragment.tag)
    }
}