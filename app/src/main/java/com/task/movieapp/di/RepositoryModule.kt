package com.task.movieapp.di

import com.task.movieapp.data.repositories.MovieRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { MovieRepository(api = get(), database = get()) }
}