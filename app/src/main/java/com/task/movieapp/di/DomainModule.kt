package com.task.movieapp.di

import com.task.movieapp.domain.movie.MovieInterceptor
import org.koin.dsl.module

val domainModule = module {
    factory { MovieInterceptor(repository = get()) }
}