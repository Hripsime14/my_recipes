package com.example.myrecipes.data.repository.repositoryimpl

import com.example.myrecipes.data.local.datasource.RecipesLocalDataSource
import com.example.myrecipes.data.repository.RecipesRepository

class RecipesRepositoryImpl(
    private val localDataSource: RecipesLocalDataSource
): RecipesRepository {
}