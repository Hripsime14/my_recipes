package com.example.myrecipes.data.local.db.mapper

import android.net.Uri
import com.example.myrecipes.data.emptyString
import com.example.myrecipes.data.getDefaultUri
import com.example.myrecipes.data.model.data.LoadedRecipesData
import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity
import com.example.myrecipes.data.model.response.ResponseRecipes
import java.util.*

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
        id = source.id
    )
}

//val RECIPE_API_DATA_TO_VIEW_DATA_MAPPER = object : Mapper<ResponseRecipes, RecipeViewData> {
//    override fun map(source: ResponseRecipes): RecipeViewData = RecipeViewData(
//        title = source.name ?: emptyString(),
//        description = source.description ?: emptyString(),
//        imageUri = source.thumbnailUrl?.let {
//            Uri.parse(it)
//        } ?: getDefaultUri(),
//        id = source.showId ?: 0
//    )
//}

val RECIPE_API_DATA_TO_LOADED_RECIPES_DATA_MAPPER = object : Mapper<ResponseRecipes, LoadedRecipesData> {
    override fun map(source: ResponseRecipes): LoadedRecipesData = LoadedRecipesData(
        title = source.name ?: emptyString(),
        description = source.description ?: emptyString(),
        imageUri = source.thumbnailUrl?.let {
            Uri.parse(it)
        } ?: getDefaultUri(),
        id = UUID.randomUUID().toString()
    )
}

val LOADED_RECIPE_DATA_TO_ENTITY_MAPPER = object : Mapper<LoadedRecipesData, RecipesEntity> {
    override fun map(source: LoadedRecipesData): RecipesEntity = RecipesEntity (
        title = source.title?: emptyString(),
        description = source.description?: emptyString(),
        imageUri = source.imageUri.let {
            Uri.parse(it.toString())
        } ?: getDefaultUri(),
        id = source.id
    )
}