package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.entity.RecipesEntity


interface AddRecipeUseCase {
    suspend operator fun invoke(recipe: RecipesEntity)
}