package com.example.myrecipes.data.repository.repositoryimpl

import android.net.Uri
import com.example.myrecipes.data.local.datasource.AddRecipeLocalDataSource
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.AddRecipeRepository
import com.example.myrecipes.domain.manager.ImageProviderManager

class AddRecipeRepositoryImpl(
    private val localDataSource: AddRecipeLocalDataSource,
    private val imageProviderManager: ImageProviderManager
): AddRecipeRepository {
    override suspend fun addRecipe(recipe: RecipesEntity) = localDataSource.addRecipe(recipe)
    override suspend fun getImageUriFromInternalStorage(externalUri: Uri): Uri? = imageProviderManager.getInternalStorageImageUri(externalUri)

}