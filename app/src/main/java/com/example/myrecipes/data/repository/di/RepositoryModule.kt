package com.example.myrecipes.data.repository.di

import com.example.myrecipes.data.repository.AddRecipeRepository
import com.example.myrecipes.data.repository.RecipeDetailsRepository
import com.example.myrecipes.data.repository.RecipesRepository
import com.example.myrecipes.data.repository.repositoryimpl.AddRecipeRepositoryImpl
import com.example.myrecipes.data.repository.repositoryimpl.RecipeDetailsRepositoryImpl
import com.example.myrecipes.data.repository.repositoryimpl.RecipesRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {
    factory<RecipesRepository> {
        RecipesRepositoryImpl(
            get()
        )
    }

    factory<RecipeDetailsRepository> {
        RecipeDetailsRepositoryImpl(
            localDataSource = get(),
            imageProviderManager = get()
        )
    }

    factory<AddRecipeRepository> {
        AddRecipeRepositoryImpl(
            localDataSource = get(),
            imageProviderManager = get()
        )
    }
}