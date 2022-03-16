package com.example.myrecipes.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myrecipes.data.local.RECIPE_TABLE_NAME
import com.example.myrecipes.data.model.entity.RecipesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addRecipe(recipe: RecipesEntity)

    @Query("SELECT * FROM $RECIPE_TABLE_NAME ORDER BY id DESC")
    fun getAllRecipes(): Flow<List<RecipesEntity>>
}