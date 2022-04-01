package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.local.db.mapper.RECIPE_ENTITY_TO_VIEW_DATA_MAPPER
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.RecipesRepository
import com.example.myrecipes.data.repository.di.repositoryModule
import com.example.myrecipes.domain.usecase.GetRecipesUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecipesUseCaseImpl(private val recipesRepository: RecipesRepository): GetRecipesUseCase {
    override fun invoke(): Flow<List<RecipeViewData>> = recipesRepository.getAllRecipes().map {
            RECIPE_ENTITY_TO_VIEW_DATA_MAPPER.map(it)
    }
}