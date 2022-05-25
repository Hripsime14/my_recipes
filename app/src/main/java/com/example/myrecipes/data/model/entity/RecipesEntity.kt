package com.example.myrecipes.data.model.entity

import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.myrecipes.data.local.RECIPE_TABLE_NAME

@Entity(tableName = RECIPE_TABLE_NAME)
data class RecipesEntity(
    val title: String,
    val description: String,
    val imageUri: Uri,
    @PrimaryKey
    val id: String
    )