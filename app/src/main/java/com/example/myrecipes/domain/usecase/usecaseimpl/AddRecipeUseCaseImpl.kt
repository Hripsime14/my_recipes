package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.AddRecipeRepository
import com.example.myrecipes.domain.usecase.AddRecipeUseCase

class AddRecipeUseCaseImpl(private val addRecipeRepository: AddRecipeRepository): AddRecipeUseCase {

    override suspend fun invoke(recipe: RecipesEntity) = addRecipeRepository.addRecipe(recipe)
}