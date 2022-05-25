package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.local.db.mapper.RECIPE_VIEW_DATA_TO_ENTITY_MAPPER
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.LoadRecipesRepository
import com.example.myrecipes.domain.usecase.AddRecipesListUseCase

class AddRecipesListUseCaseImpl(private val loadRecipesRepository: LoadRecipesRepository): AddRecipesListUseCase {
    override suspend fun invoke(recipes: List<RecipeViewData>) =
        loadRecipesRepository.addRecipes(RECIPE_VIEW_DATA_TO_ENTITY_MAPPER.map(recipes))
}