package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.entity.RecipesEntity

interface UpdateRecipeUseCase {
    suspend operator fun invoke(recipe: RecipesEntity)
}