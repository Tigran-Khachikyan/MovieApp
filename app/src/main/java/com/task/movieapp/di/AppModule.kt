package com.task.movieapp.di

import com.task.movieapp.data.db.AppDatabase
import com.task.movieapp.data.repositories.MovieRepository
import com.task.movieapp.domain.movie.MovieInteractor
import com.task.movieapp.ui.details.DetailsViewModel
import com.task.movieapp.ui.home.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase.create(androidContext()) }

    single { MovieRepository(api = get(), database = get()) }
    factory { MovieInteractor(repository = get()) }
    viewModel { HomeViewModel(interactor = get()) }
    viewModel { DetailsViewModel(interactor = get()) }
}
