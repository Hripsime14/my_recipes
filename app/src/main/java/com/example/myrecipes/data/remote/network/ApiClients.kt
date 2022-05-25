package com.example.myrecipes.data.remote.network

import androidx.room.Dao
import com.example.myrecipes.data.model.response.ResponseGeneral
import com.example.myrecipes.data.remote.RECIPES
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET

@Dao
interface ApiClients {

    @GET(RECIPES)
    suspend fun loadRecipesList(): Response<ResponseGeneral>

}