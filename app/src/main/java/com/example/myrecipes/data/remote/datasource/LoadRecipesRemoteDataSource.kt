package com.example.myrecipes.data.remote.datasource

import com.example.myrecipes.data.model.response.ResponseGeneral
import retrofit2.Response

interface LoadRecipesRemoteDataSource {
    suspend fun loadRecipes(): Response<ResponseGeneral>
}