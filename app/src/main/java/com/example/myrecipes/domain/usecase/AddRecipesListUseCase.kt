package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.data.LoadedRecipesData

interface AddRecipesListUseCase {
    suspend operator fun invoke(recipes: List<LoadedRecipesData>)
}