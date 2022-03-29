package com.example.myrecipes.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myrecipes.data.local.db.converter.Converters
import com.example.myrecipes.data.local.db.dao.RecipeDao
import com.example.myrecipes.data.model.entity.RecipesEntity


@Database(
    entities = [RecipesEntity::class],
    version = 2
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}