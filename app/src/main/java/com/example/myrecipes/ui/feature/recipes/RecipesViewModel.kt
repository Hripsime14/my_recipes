package com.example.myrecipes.ui.feature.recipes

import com.example.myrecipes.domain.usecase.GetRecipesUseCase
import com.example.myrecipes.ui.common.BaseViewModel

class RecipesViewModel(
    private val getRecipesUseCase: GetRecipesUseCase
) : BaseViewModel() {

    fun getAllRecipes() = getRecipesUseCase()
}