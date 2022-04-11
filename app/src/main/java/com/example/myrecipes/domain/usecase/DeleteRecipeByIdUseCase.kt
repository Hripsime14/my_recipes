package com.example.myrecipes.domain.usecase

interface DeleteRecipeByIdUseCase {
    suspend operator fun invoke(recipeId: Int)
}