package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.repository.RecipesRepository
import com.example.myrecipes.domain.usecase.DeleteRecipeByIdUseCase

class DeleteRecipeByIdUseCaseImpl (private val recipesRepository: RecipesRepository): DeleteRecipeByIdUseCase {
    override suspend fun invoke(recipeId: Int) = recipesRepository.deleteRecipeById(recipeId)
}