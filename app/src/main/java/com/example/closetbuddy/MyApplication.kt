package com.example.closetbuddy

import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.util.CoilUtils
import com.example.closetbuddy.repository.ClothesRepository
import com.example.closetbuddy.room.CBDatabase
import com.example.closetbuddy.utils.SharedPrefsHelpers
import okhttp3.OkHttpClient

class MyApplication : Application(), ImageLoaderFactory {

    override fun onCreate() {
        super.onCreate()
        SharedPrefsHelpers.init(applicationContext)
        CBDb = CBDatabase.getInstance(applicationContext)
        clothesRepository = ClothesRepository(CBDb.clothesDao())
    }

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(applicationContext)
                .crossfade(true)
                .crossfade(300)
                .okHttpClient {
                    OkHttpClient.Builder()
                    .cache(CoilUtils.createDefaultCache(applicationContext))
                    .build()
                }
                .build()
    }

    companion object {
        lateinit var CBDb: CBDatabase
        lateinit var clothesRepository: ClothesRepository
    }
}