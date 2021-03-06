package com.example.myrecipes.ui.feature.di

import com.example.myrecipes.ui.feature.addrecipe.AddRecipeViewModel
import com.example.myrecipes.ui.feature.recipedetails.RecipeDetailsViewModel
import com.example.myrecipes.ui.feature.recipes.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel {
        RecipesViewModel(get())
    }

    viewModel {
        RecipeDetailsViewModel(get(), get(), get())
    }

    viewModel {
        AddRecipeViewModel(addRecipeUseCase = get(), getInternalStorageImageUriUseCase = get())
    }
}