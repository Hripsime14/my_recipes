package com.example.myrecipes.domain.usecase

import android.net.Uri

interface GetInternalStorageImageUriUseCase {
    suspend operator fun invoke(externalUri: Uri): Uri?

}