package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.data.RecipeViewData

interface AddRecipesListUseCase {
    suspend operator fun invoke(recipes: List<RecipeViewData>)
}