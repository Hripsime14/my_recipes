package com.example.myrecipes.data.local.datasource.datasourceimpl

import com.example.myrecipes.data.local.datasource.AddRecipeLocalDataSource
import com.example.myrecipes.data.local.db.dao.RecipeDao
import com.example.myrecipes.data.model.entity.RecipesEntity

class AddRecipeLocalDataSourceImpl(
    private val recipeDao: RecipeDao
): AddRecipeLocalDataSource {
    override suspend fun addRecipe(recipe: RecipesEntity) = recipeDao.addRecipe(recipe)
}