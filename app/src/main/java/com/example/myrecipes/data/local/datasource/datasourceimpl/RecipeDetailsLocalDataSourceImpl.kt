package com.example.myrecipes.data.local.datasource.datasourceimpl

import com.example.myrecipes.data.local.datasource.RecipeDetailsLocalDataSource
import com.example.myrecipes.data.local.db.dao.RecipeDao
import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

class RecipeDetailsLocalDataSourceImpl(
    private val recipeDao: RecipeDao
): RecipeDetailsLocalDataSource {
    override suspend fun getRecipeById(recipeId: String): RecipesEntity? = recipeDao.getRecipeById(recipeId)
    override suspend fun updateRecipe(recipe: RecipesEntity) = recipeDao.updateRecipe(recipe)
}