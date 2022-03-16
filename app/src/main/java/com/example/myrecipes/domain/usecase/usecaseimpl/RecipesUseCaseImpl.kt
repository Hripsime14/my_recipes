package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.RecipesRepository
import com.example.myrecipes.domain.usecase.RecipesUseCase
import kotlinx.coroutines.flow.Flow

class RecipesUseCaseImpl(private val recipesRepository: RecipesRepository): RecipesUseCase {
    override fun invoke(): Flow<List<RecipesEntity>> = recipesRepository.getAllRecipes()
}