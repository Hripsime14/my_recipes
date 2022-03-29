package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.RecipeDetailsRepository
import com.example.myrecipes.domain.usecase.UpdateRecipeUseCase

class UpdateRecipeUseCaseImpl(private val recipeDetailsRepository: RecipeDetailsRepository): UpdateRecipeUseCase {
    override suspend fun invoke(recipe: RecipesEntity) = recipeDetailsRepository.updateRecipe(recipe)
}