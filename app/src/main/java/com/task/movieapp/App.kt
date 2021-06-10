package com.task.movieapp

import android.app.Application
import com.task.movieapp.data.network.ApiProvider
import com.task.movieapp.di.appModule
import com.task.movieapp.di.domainModule
import com.task.movieapp.di.networkModule
import com.task.movieapp.di.repositoryModule
import org.koin.android.ext.android.get
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    domainModule,
                    networkModule,
                    repositoryModule
                )
            )
        }
        (get<ApiProvider>()).init()
    }
}