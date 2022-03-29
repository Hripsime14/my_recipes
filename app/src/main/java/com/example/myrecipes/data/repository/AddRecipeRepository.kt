package com.example.myrecipes.data.repository

import android.net.Uri
import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface AddRecipeRepository {
    suspend fun addRecipe(recipe: RecipesEntity)
    suspend fun getImageUriFromInternalStorage(externalUri: Uri): Uri?
}