package com.example.myrecipes.ui.feature.addrecipe

import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    private val addRecipeUseCase: AddRecipeUseCase,
): BaseViewModel() {
    private val _recipeSharedFlow = MutableSharedFlow<RecipesEntity>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val recipeSharedFlow = _recipeSharedFlow.asSharedFlow()

    fun addRecipe(recipe: RecipesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase(recipe)
        }
    }
}