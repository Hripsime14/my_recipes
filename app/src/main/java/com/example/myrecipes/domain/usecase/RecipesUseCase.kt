package com.example.myrecipes.domain.usecase

import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

interface RecipesUseCase {
    operator fun invoke(): Flow<List<RecipesEntity>>
}