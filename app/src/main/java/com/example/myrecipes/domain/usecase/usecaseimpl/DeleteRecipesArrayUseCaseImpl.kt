package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.local.db.mapper.RECIPE_VIEW_DATA_TO_ENTITY_MAPPER
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.repository.RecipesRepository
import com.example.myrecipes.domain.usecase.DeleteRecipesArrayUseCase

class DeleteRecipesArrayUseCaseImpl(private val recipesRepository: RecipesRepository) :
    DeleteRecipesArrayUseCase {
    override suspend fun invoke(recipes: List<RecipeViewData>) {
        recipesRepository.deleteRecipes(
            RECIPE_VIEW_DATA_TO_ENTITY_MAPPER.map(recipes).toTypedArray()
        )
    }
}