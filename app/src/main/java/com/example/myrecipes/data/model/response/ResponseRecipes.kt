package com.example.myrecipes.data.model.response

import com.google.gson.annotations.SerializedName

data class ResponseRecipes(
    @SerializedName("show_id")
    val showId: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("thumbnail_url")
    val thumbnailUrl: String?,
    @SerializedName("description")
    val description: String?
)
