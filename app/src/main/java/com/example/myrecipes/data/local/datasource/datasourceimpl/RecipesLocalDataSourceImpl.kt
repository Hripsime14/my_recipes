package com.example.myrecipes.data.local.datasource.datasourceimpl

import com.example.myrecipes.data.local.datasource.RecipesLocalDataSource
import com.example.myrecipes.data.local.db.dao.RecipeDao

class RecipesLocalDataSourceImpl(
    private val recipeDao: RecipeDao
): RecipesLocalDataSource {
}