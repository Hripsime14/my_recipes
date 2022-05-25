package com.example.myrecipes.data.remote.datasource.datasourceimpl

import com.example.myrecipes.data.remote.datasource.LoadRecipesRemoteDataSource
import com.example.myrecipes.data.remote.network.ApiClients

class LoadRecipesRemoteDataSourceImpl(
    private val apiClients: ApiClients
): LoadRecipesRemoteDataSource {

    override suspend fun loadRecipes() = apiClients.loadRecipesList()
}