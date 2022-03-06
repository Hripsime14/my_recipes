package com.example.myrecipes.data.local.datasource.datasourceimpl

import com.example.myrecipes.data.local.datasource.RecipeDetailsLocalDataSource
import com.example.myrecipes.data.local.db.dao.RecipeDao

class RecipeDetailsLocalDataSourceImpl(
    private val recipeDao: RecipeDao
): RecipeDetailsLocalDataSource {
}