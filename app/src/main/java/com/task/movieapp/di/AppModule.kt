package com.task.movieapp.di

import com.task.movieapp.data.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.create(androidContext()) }
}
