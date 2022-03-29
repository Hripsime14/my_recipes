package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface GetRecipeByIdUseCase {
    operator fun invoke(recipeId: Int): Flow<RecipesEntity>
}