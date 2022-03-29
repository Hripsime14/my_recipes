package com.example.myrecipes.domain.manager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import androidx.core.net.toUri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*

class ImageProviderManager(private val context: Context) {

    suspend fun getInternalStorageImageUri(uri: Uri): Uri? {
        return withContext(Dispatchers.IO) {
            val bitmap = getBitmapFromUri(uri)
            val name = UUID.randomUUID().toString()
            bitmap?.let {
                if (savePhotoToInternalStorage(name, it)) {
                    getPhotoFromInternalStorageByName(name)
                } else null
            }
        }
    }

    private suspend fun getBitmapFromUri(uri: Uri): Bitmap? {
        return withContext(Dispatchers.IO) {
            context.contentResolver?.openInputStream(uri)?.use {
                BitmapFactory.decodeStream(it)
            }
        }
    }

    private suspend fun savePhotoToInternalStorage(fileName: String, bitmap: Bitmap): Boolean {
        return withContext(Dispatchers.IO) {
            try {
                context.openFileOutput("$fileName.jpg", AppCompatActivity.MODE_PRIVATE)
                    .use { stream ->
                        if (!bitmap.compress(Bitmap.CompressFormat.JPEG, 95, stream)) {
                            throw IOException("Couldn't save bitmap.")
                        }
                    }
                true
            } catch (e: IOException) {
                e.printStackTrace()
                false
            }
        }
    }

    private suspend fun getPhotoFromInternalStorageByName(imageName: String): Uri? {
        return withContext(Dispatchers.IO) {
            val files = context.filesDir.listFiles()
            files?.filter { it.canRead() && it.isFile && it.name.endsWith("$imageName.jpg") }?.takeIf { fileList ->
                fileList.isNotEmpty()
            }?.let {
                it[0].absolutePath.toUri()
            }
        }
    }
}