package com.example.myrecipes.data.local.datasource.datasourceimpl

import com.example.myrecipes.data.local.datasource.LoadRecipesLocalDataSource
import com.example.myrecipes.data.local.db.dao.RecipeDao
import com.example.myrecipes.data.model.entity.RecipesEntity

class LoadRecipesLocalDataSourceImpl(private val recipesDao: RecipeDao): LoadRecipesLocalDataSource {
    override suspend fun addRecipes(recipes: List<RecipesEntity>) =
        recipesDao.addRecipes(recipes)
}