package com.example.myrecipes.data.local.datasource

import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipeDetailsLocalDataSource {

    suspend fun getRecipeById(recipeId: Int): RecipesEntity?

    suspend fun updateRecipe(recipe: RecipesEntity)
}