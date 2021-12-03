package com.example.closetbuddy

import android.os.Bundle
import androidx.activity.viewModels
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.closetbuddy.databinding.ActivityMainBinding
import com.example.closetbuddy.models.Clothing
import com.example.closetbuddy.models.ClothingViewModel
import com.example.closetbuddy.ui.details.DetailsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    // test1 clothingViewModel
    private val clothingViewModel: ClothingViewModel by viewModels {
        ClothingViewModel.provideFactory(MyApplication.clothesRepository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.navigation_outfits, R.id.navigation_addItem, R.id.navigation_closet))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
    fun openDetails(item: Clothing?) {
        clothingViewModel.selectItem(item)
        val newFragment = DetailsFragment()
        newFragment.show(supportFragmentManager, newFragment.tag)
    }
}