package com.example.myrecipes.data.repository.repositoryimpl

import com.example.myrecipes.data.local.datasource.AddRecipeLocalDataSource
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.AddRecipeRepository

class AddRecipeRepositoryImpl(
    private val localDataSource: AddRecipeLocalDataSource
): AddRecipeRepository {
    override suspend fun addRecipe(recipe: RecipesEntity) = localDataSource.addRecipe(recipe)
}