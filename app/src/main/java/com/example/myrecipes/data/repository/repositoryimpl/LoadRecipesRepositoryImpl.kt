package com.example.myrecipes.data.repository.repositoryimpl

import com.example.myrecipes.data.local.datasource.LoadRecipesLocalDataSource
import com.example.myrecipes.data.local.db.mapper.RECIPE_API_DATA_TO_LOADED_RECIPES_DATA_MAPPER
import com.example.myrecipes.data.model.data.LoadedRecipesData
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.remote.datasource.LoadRecipesRemoteDataSource
import com.example.myrecipes.data.repository.LoadRecipesRepository
import com.example.myrecipes.data.repository.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LoadRecipesRepositoryImpl(private val loadRecipesRemoteDataSource: LoadRecipesRemoteDataSource,
private val loadRecipesLocalDataSource: LoadRecipesLocalDataSource): LoadRecipesRepository {

    override fun loadRecipes(): Flow<Resource<List<LoadedRecipesData>>> = flow {
        emit(Resource.Loading(null))

        val response = loadRecipesRemoteDataSource.loadRecipes()
        if (response.isSuccessful && response.body()?.count != null /*&& response.body()?.count.toInt() > 0*/) {
            response.body()?.results?.let {
                emit(Resource.Success(RECIPE_API_DATA_TO_LOADED_RECIPES_DATA_MAPPER.map(it)))
            }
        } else {
            emit(Resource.Error(response.message(), null, response.code()))
        }
    }

    override suspend fun addRecipes(recipes: List<RecipesEntity>) =
        loadRecipesLocalDataSource.addRecipes(recipes)
}