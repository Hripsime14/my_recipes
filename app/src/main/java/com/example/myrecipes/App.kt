package com.example.myrecipes

import android.app.Application
import com.example.myrecipes.di.diModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class App: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(diModule)
        }
    }
}