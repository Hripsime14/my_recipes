package com.example.myrecipes.data.model.data

import android.net.Uri

data class RecipeViewData(
    val title: String,
    val description: String,
    val imageUri: Uri,
    val id: String,
    var isSelected: Boolean = false
)