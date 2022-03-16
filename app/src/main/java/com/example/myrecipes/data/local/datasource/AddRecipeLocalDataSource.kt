package com.example.myrecipes.data.local.datasource

import com.example.myrecipes.data.model.entity.RecipesEntity

interface AddRecipeLocalDataSource {
    suspend fun addRecipe(recipe: RecipesEntity)
}