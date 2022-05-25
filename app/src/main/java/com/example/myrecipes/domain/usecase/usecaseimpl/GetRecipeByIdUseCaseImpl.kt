package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.RecipeDetailsRepository
import com.example.myrecipes.domain.usecase.GetRecipeByIdUseCase
import kotlinx.coroutines.flow.Flow

class GetRecipeByIdUseCaseImpl(private val recipeDetailsRepository: RecipeDetailsRepository): GetRecipeByIdUseCase {
    override fun invoke(recipeId: String): Flow<RecipesEntity> = recipeDetailsRepository.getRecipeById(recipeId)
}