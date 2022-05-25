package com.example.myrecipes.data.remote.di

import com.example.myrecipes.data.remote.datasource.LoadRecipesRemoteDataSource
import com.example.myrecipes.data.remote.datasource.datasourceimpl.LoadRecipesRemoteDataSourceImpl
import com.example.myrecipes.data.remote.network.ApiClients
import com.example.myrecipes.data.remote.network.interceptor.ContentTypeInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

fun remoteModule(baseUrl: String) = module {

    factory<Interceptor> {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.apply {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        }
    }

    single {
        return@single ContentTypeInterceptor()
    }

    factory {
        OkHttpClient.Builder().readTimeout(100, TimeUnit.SECONDS)
            .connectTimeout(100, TimeUnit.SECONDS)
            .addInterceptor(get<Interceptor>())
            .addInterceptor(get<ContentTypeInterceptor>())
            .build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    factory {
        get<Retrofit>().create(ApiClients::class.java)
    }

    factory<LoadRecipesRemoteDataSource> { LoadRecipesRemoteDataSourceImpl(apiClients = get()) }
}