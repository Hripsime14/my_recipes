package com.example.myrecipes.ui.feature.recipes

import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.domain.usecase.DeleteRecipeByIdUseCase
import com.example.myrecipes.domain.usecase.DeleteRecipesArrayUseCase
import com.example.myrecipes.domain.usecase.GetRecipesUseCase
import com.example.myrecipes.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RecipesViewModel(
    private val getRecipesUseCase: GetRecipesUseCase,
    private val deleteRecipeByIdUseCase: DeleteRecipeByIdUseCase,
    private val deleteRecipesArrayUseCase: DeleteRecipesArrayUseCase
) : BaseViewModel() {

    fun getAllRecipes() = getRecipesUseCase()

    fun deleteRecipeById(recipeId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteRecipeByIdUseCase(recipeId)
        }
    }

    fun deleteRecipesArray(recipesArray: List<RecipeViewData>) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteRecipesArrayUseCase(recipesArray)
        }
    }
}