package com.example.myrecipes.data.model.data

import android.net.Uri

data class LoadedRecipesData(
    val id: String,
    val title: String?,
    val imageUri: Uri,
    val description: String?,
    var isSelected: Boolean = false
)
