package com.example.myrecipes.domain.di

import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.RecipesUseCase
import com.example.myrecipes.domain.usecase.usecaseimpl.AddRecipeUseCaseImpl
import com.example.myrecipes.domain.usecase.usecaseimpl.RecipesUseCaseImpl
import org.koin.dsl.module

val domainModule = module {
    factory<AddRecipeUseCase> { AddRecipeUseCaseImpl(addRecipeRepository = get()) }
    factory<RecipesUseCase> { RecipesUseCaseImpl(recipesRepository = get()) }
}