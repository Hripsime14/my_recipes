package com.example.myrecipes.data.local.db.mapper

import com.example.myrecipes.data.model.data.RecipeViewData
import com.example.myrecipes.data.model.entity.RecipesEntity

val RECIPE_ENTITY_TO_VIEW_DATA_MAPPER = object : Mapper<RecipesEntity, RecipeViewData> {
    override fun map(source: RecipesEntity): RecipeViewData = RecipeViewData(
        title = source.title,
        description = source.description,
        imageUri = source.imageUri,
        id = source.id,
        isSelected = false
    )
}