package com.example.closetbuddy.utils

import android.content.Context
import android.os.Environment
import com.example.closetbuddy.ui.camera.CameraFragment
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object UseCases {

    fun createImageFile(context: Context?): File {
        val storageDir: File? = context?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(
            storageDir,
            SimpleDateFormat(
                CameraFragment.FILENAME_FORMAT, Locale.US
            ).format(System.currentTimeMillis()) + ".jpg")
    }
}