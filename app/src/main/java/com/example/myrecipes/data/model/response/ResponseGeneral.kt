package com.example.myrecipes.data.model.response
import com.google.gson.annotations.SerializedName


data class ResponseGeneral(
    @SerializedName("count")
    val count: Int,
    @SerializedName("results")
    val results: List<ResponseRecipes>
)