package com.example.myrecipes.data.repository

import android.net.Uri
import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipeDetailsRepository {
    fun getRecipeById(recipeId: String): Flow<RecipesEntity>
    suspend fun updateRecipe(recipe: RecipesEntity)
    suspend fun getImageUriFromInternalStorage(externalUri: Uri): Uri?
}