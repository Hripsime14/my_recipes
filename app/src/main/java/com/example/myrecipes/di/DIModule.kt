package com.example.myrecipes.di

import com.example.myrecipes.data.local.di.localModule
import com.example.myrecipes.data.repository.di.repositoryModule
import com.example.myrecipes.domain.di.domainModule
import com.example.myrecipes.ui.feature.di.featureModule
import org.koin.core.module.Module

val diModule = listOf<Module>(
    localModule,
    repositoryModule,
    featureModule,
    domainModule
)