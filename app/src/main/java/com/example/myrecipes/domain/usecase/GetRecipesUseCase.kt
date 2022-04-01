package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface GetRecipesUseCase {
    operator fun invoke(): Flow<List<RecipeViewData>>
}