package com.example.myrecipes.data.local.db.mapper

import android.net.Uri
import com.example.myrecipes.data.emptyString
import com.example.myrecipes.data.getDefaultUri
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.model.response.ResponseRecipes

val RECIPE_ENTITY_TO_VIEW_DATA_MAPPER = object : Mapper<RecipesEntity, RecipeViewData> {
    override fun map(source: RecipesEntity): RecipeViewData = RecipeViewData(
        title = source.title,
        description = source.description,
        imageUri = source.imageUri,
        id = source.id,
        isSelected = false
    )
}

val RECIPE_VIEW_DATA_TO_ENTITY_MAPPER = object : Mapper<RecipeViewData, RecipesEntity> {
    override fun map(source: RecipeViewData): RecipesEntity = RecipesEntity(
        title = source.title,
        description = source.description,
        imageUri = source.imageUri,
        id = source.id,
    )
}

val RECIPE_API_DATA_TO_VIEW_DATA_MAPPER = object : Mapper<ResponseRecipes, RecipeViewData> {
    override fun map(source: ResponseRecipes): RecipeViewData = RecipeViewData(
        title = source.name ?: emptyString(),
        description = source.description ?: emptyString(),
        imageUri = source.thumbnailUrl?.let {
            Uri.parse(it)
        } ?: getDefaultUri(),
        id = source.showId ?: 0
    )
}