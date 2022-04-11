package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.data.RecipeViewData

interface DeleteRecipesArrayUseCase {
    suspend operator fun invoke(recipes: List<RecipeViewData>)
}