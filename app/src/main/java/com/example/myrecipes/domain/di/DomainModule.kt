package com.example.myrecipes.domain.di

import com.example.myrecipes.domain.manager.ImageProviderManager
import com.example.myrecipes.domain.usecase.*
import com.example.myrecipes.domain.usecase.usecaseimpl.*
import org.koin.dsl.module

val domainModule = module {
    factory<AddRecipeUseCase> { AddRecipeUseCaseImpl(addRecipeRepository = get()) }
    factory<GetRecipesUseCase> { GetRecipesUseCaseImpl(recipesRepository = get()) }
    factory<GetRecipeByIdUseCase> { GetRecipeByIdUseCaseImpl(recipeDetailsRepository = get()) }
    factory<UpdateRecipeUseCase> { UpdateRecipeUseCaseImpl(recipeDetailsRepository = get()) }
    factory<GetInternalStorageImageUriUseCase> { GetInternalStorageImageUriUseCaseImpl(recipeDetailsRepository   = get()) }
    factory<DeleteRecipeByIdUseCase> { DeleteRecipeByIdUseCaseImpl(recipesRepository = get()) }
    factory<DeleteRecipesArrayUseCase> { DeleteRecipesArrayUseCaseImpl(recipesRepository = get()) }
    single { ImageProviderManager(get()) }
}