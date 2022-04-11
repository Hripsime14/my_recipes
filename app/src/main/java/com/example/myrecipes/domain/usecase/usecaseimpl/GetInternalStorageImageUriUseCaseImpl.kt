package com.example.myrecipes.domain.usecase.usecaseimpl;

import android.net.Uri
import com.example.myrecipes.data.repository.RecipeDetailsRepository
import com.example.myrecipes.domain.manager.ImageProviderManager
import com.example.myrecipes.domain.usecase.GetInternalStorageImageUriUseCase

class GetInternalStorageImageUriUseCaseImpl(private val recipeDetailsRepository: RecipeDetailsRepository): GetInternalStorageImageUriUseCase {
    override suspend fun invoke(externalUri: Uri): Uri? = recipeDetailsRepository.getImageUriFromInternalStorage(externalUri)
}