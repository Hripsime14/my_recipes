package com.example.myrecipes.data.local.datasource

import com.example.myrecipes.data.model.entity.RecipesEntity

interface LoadRecipesLocalDataSource {
    suspend fun addRecipes(recipes: List<RecipesEntity>)
}