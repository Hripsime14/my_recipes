package com.example.myrecipes.data.repository.repositoryimpl

import com.example.myrecipes.data.local.datasource.RecipesLocalDataSource
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.RecipesRepository
import kotlinx.coroutines.flow.Flow

class RecipesRepositoryImpl(
    private val localDataSource: RecipesLocalDataSource
): RecipesRepository {
    override fun getAllRecipes(): Flow<List<RecipesEntity>> = localDataSource.getAllRecipes()
}