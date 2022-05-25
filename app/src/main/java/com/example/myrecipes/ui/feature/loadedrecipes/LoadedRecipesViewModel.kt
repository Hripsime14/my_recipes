package com.example.myrecipes.ui.feature.loadedrecipes

import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.model.data.LoadedRecipesData
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.repository.util.Resource
import com.example.myrecipes.domain.usecase.AddRecipesListUseCase
import com.example.myrecipes.domain.usecase.LoadRecipesUseCase
import com.example.myrecipes.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.launch

class LoadedRecipesViewModel(private val loadRecipesUseCase: LoadRecipesUseCase,
private val addRecipesListUseCase: AddRecipesListUseCase): BaseViewModel() {
    private val _recipeSharedFlow = MutableSharedFlow<Resource<List<LoadedRecipesData>>>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val recipeSharedFlow = _recipeSharedFlow.asSharedFlow()

    fun loadRecipes() {
        viewModelScope.launch(Dispatchers.IO) {
            _recipeSharedFlow.emitAll(loadRecipesUseCase())
        }
    }

    fun addRecipes(recipes: List<LoadedRecipesData>) {
        viewModelScope.launch(Dispatchers.IO) {
            addRecipesListUseCase(recipes)
        }
    }
}