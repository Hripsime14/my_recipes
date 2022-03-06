package com.example.myrecipes.ui.feature.di

import com.example.myrecipes.ui.feature.recipedetails.RecipeDetailsViewModel
import com.example.myrecipes.ui.feature.ricipes.RecipesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val featureModule = module {
    viewModel {
        RecipesViewModel()
    }

    viewModel {
        RecipeDetailsViewModel()
    }
}