package com.example.myrecipes.data.repository

import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoadRecipesRepository {
    fun loadRecipes(): Flow<Resource<List<RecipeViewData>>>
    suspend fun addRecipes(recipes: List<RecipesEntity>)
}