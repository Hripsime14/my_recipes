package com.example.myrecipes.domain.usecase.usecaseimpl

import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.repository.LoadRecipesRepository
import com.example.myrecipes.data.repository.util.Resource
import com.example.myrecipes.domain.usecase.LoadRecipesUseCase
import kotlinx.coroutines.flow.Flow

class LoadRecipesUseCaseImpl(private val loadRecipesRepository: LoadRecipesRepository): LoadRecipesUseCase {
    override fun invoke(): Flow<Resource<List<RecipeViewData>>> =
        loadRecipesRepository.loadRecipes()
}