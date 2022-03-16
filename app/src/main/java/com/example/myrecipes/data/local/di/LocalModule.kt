package com.example.myrecipes.data.local.di

import androidx.room.Room
import com.example.myrecipes.data.local.APP_DATABASE
import com.example.myrecipes.data.local.datasource.AddRecipeLocalDataSource
import com.example.myrecipes.data.local.datasource.RecipeDetailsLocalDataSource
import com.example.myrecipes.data.local.datasource.RecipesLocalDataSource
import com.example.myrecipes.data.local.datasource.datasourceimpl.AddRecipeLocalDataSourceImpl
import com.example.myrecipes.data.local.datasource.datasourceimpl.RecipeDetailsLocalDataSourceImpl
import com.example.myrecipes.data.local.datasource.datasourceimpl.RecipesLocalDataSourceImpl
import com.example.myrecipes.data.local.db.AppDatabase
import com.example.myrecipes.data.repository.RecipesRepository
import com.example.myrecipes.data.repository.repositoryimpl.RecipesRepositoryImpl
import org.koin.dsl.module

val localModule = module {

    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            APP_DATABASE
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    fun provideRecipeDao(database: AppDatabase) = database.recipeDao()

    single {
        provideRecipeDao(database = get())
    }

    factory<RecipesLocalDataSource> {
        RecipesLocalDataSourceImpl(
            recipeDao = get()
        )
    }

    factory<RecipeDetailsLocalDataSource> {
        RecipeDetailsLocalDataSourceImpl(
            recipeDao = get()
        )
    }

    factory<AddRecipeLocalDataSource> {
        AddRecipeLocalDataSourceImpl(
            recipeDao = get()
        )
    }
}