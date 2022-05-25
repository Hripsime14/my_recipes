package com.example.myrecipes.ui.feature.recipedetails

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.domain.manager.ImageProviderManager
import com.example.myrecipes.domain.usecase.GetInternalStorageImageUriUseCase
import com.example.myrecipes.domain.usecase.GetRecipeByIdUseCase
import com.example.myrecipes.domain.usecase.UpdateRecipeUseCase
import com.example.myrecipes.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class RecipeDetailsViewModel(
    private val getRecipeByIdUseCase: GetRecipeByIdUseCase,
    private val updateRecipeUseCase: UpdateRecipeUseCase,
    private val getInternalStorageImageUriUseCase: GetInternalStorageImageUriUseCase
): BaseViewModel() {
    private val _recipeSharedFlow = MutableSharedFlow<RecipesEntity>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val recipeSharedFlow = _recipeSharedFlow.asSharedFlow()

    private val _externalStorageUriSharedFlow = MutableSharedFlow<Uri?>(
        replay = 1,
        onBufferOverflow = BufferOverflow.DROP_OLDEST
    )
    val externalStorageUriSharedFlow = _externalStorageUriSharedFlow.asSharedFlow()


    fun getRecipeById(recipeId: Int) = viewModelScope.launch(Dispatchers.IO) {
        _recipeSharedFlow.emitAll(getRecipeByIdUseCase(recipeId))
    }

    fun updateRecipe(recipe: RecipesEntity) =
        viewModelScope.launch(Dispatchers.IO) {
            updateRecipeUseCase(recipe)
        }

        fun emitImageUriFromInternalStorage(externalUri: Uri) {
            viewModelScope.launch {
                val uri = getInternalStorageImageUriUseCase(externalUri)
                _externalStorageUriSharedFlow.emit(uri)
            }
        }
    }