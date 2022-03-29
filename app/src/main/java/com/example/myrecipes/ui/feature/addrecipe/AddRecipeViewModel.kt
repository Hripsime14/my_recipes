package com.example.myrecipes.ui.feature.addrecipe

import android.net.Uri
import androidx.lifecycle.viewModelScope
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.domain.manager.ImageProviderManager
import com.example.myrecipes.domain.usecase.AddRecipeUseCase
import com.example.myrecipes.domain.usecase.GetInternalStorageImageUriUseCase
import com.example.myrecipes.ui.common.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch

class AddRecipeViewModel(
    private val addRecipeUseCase: AddRecipeUseCase,
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

    fun addRecipe(recipe: RecipesEntity) {
        viewModelScope.launch(Dispatchers.IO) {
            addRecipeUseCase(recipe)
        }
    }

    fun emitImageUriFromInternalStorage(externalUri: Uri) {
        viewModelScope.launch {
            val uri = getInternalStorageImageUriUseCase(externalUri)
            _externalStorageUriSharedFlow.emit(uri)
        }
    }
}