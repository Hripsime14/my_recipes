package com.example.myrecipes.data.local.datasource.datasourceimpl

import com.example.myrecipes.data.local.datasource.RecipesLocalDataSource
import com.example.myrecipes.data.local.db.dao.RecipeDao
import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

class RecipesLocalDataSourceImpl(
    private val recipeDao: RecipeDao
): RecipesLocalDataSource {
    override fun getAllRecipes(): Flow<List<RecipesEntity>> = recipeDao.getAllRecipes()
}