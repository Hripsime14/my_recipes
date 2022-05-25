package com.example.myrecipes.data.repository.repositoryimpl

import android.net.Uri
import com.example.myrecipes.data.local.datasource.RecipeDetailsLocalDataSource
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.repository.RecipeDetailsRepository
import com.example.myrecipes.domain.manager.ImageProviderManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RecipeDetailsRepositoryImpl(
    private val localDataSource: RecipeDetailsLocalDataSource,
    private val imageProviderManager: ImageProviderManager
): RecipeDetailsRepository {
    override fun getRecipeById(recipeId: String): Flow<RecipesEntity> = flow{
        val recipe = localDataSource.getRecipeById(recipeId)
        if (recipe != null) {
            emit(recipe)
        }
    }

    override suspend fun updateRecipe(recipe: RecipesEntity) = localDataSource.updateRecipe(recipe)
    override suspend fun getImageUriFromInternalStorage(externalUri: Uri): Uri? = imageProviderManager.getInternalStorageImageUri(externalUri)


}