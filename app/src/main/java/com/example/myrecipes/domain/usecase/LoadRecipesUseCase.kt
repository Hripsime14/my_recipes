package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow

interface LoadRecipesUseCase {
    operator fun invoke(): Flow<Resource<List<RecipeViewData>>>
}