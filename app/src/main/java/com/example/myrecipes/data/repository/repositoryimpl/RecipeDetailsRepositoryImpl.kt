package com.example.myrecipes.data.repository.repositoryimpl

import com.example.myrecipes.data.local.datasource.RecipeDetailsLocalDataSource
import com.example.myrecipes.data.repository.RecipeDetailsRepository

class RecipeDetailsRepositoryImpl(
    private val localDataSource: RecipeDetailsLocalDataSource
): RecipeDetailsRepository {
}