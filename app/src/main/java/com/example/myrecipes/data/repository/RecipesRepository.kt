package com.example.myrecipes.data.repository

import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesRepository {
    fun getAllRecipes(): Flow<List<RecipesEntity>>
}