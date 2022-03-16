package com.example.myrecipes.ui.feature.recipes

import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.domain.usecase.RecipesUseCase
import com.example.myrecipes.ui.common.BaseViewModel
import kotlinx.coroutines.launch
import java.util.concurrent.Flow

class RecipesViewModel(
    private val recipesUseCase: RecipesUseCase
) : BaseViewModel() {

    fun getAllRecipes() = recipesUseCase()
}