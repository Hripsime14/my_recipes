package com.example.myrecipes.data.local.datasource

import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesLocalDataSource {
    fun getAllRecipes(): Flow<List<RecipesEntity>>
}