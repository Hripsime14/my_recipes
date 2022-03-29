package com.example.myrecipes.domain.usecase.usecaseimpl;

import android.net.Uri
import com.example.myrecipes.domain.manager.ImageProviderManager
import com.example.myrecipes.domain.usecase.GetInternalStorageImageUriUseCase

class GetInternalStorageImageUriUseCaseImpl(private val imageProviderManager: ImageProviderManager): GetInternalStorageImageUriUseCase {
    override suspend fun invoke(externalUri: Uri): Uri? = imageProviderManager.getInternalStorageImageUri(externalUri)
}