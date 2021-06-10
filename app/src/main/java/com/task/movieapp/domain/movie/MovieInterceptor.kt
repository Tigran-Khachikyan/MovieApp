package com.task.movieapp.domain.movie

import com.task.movieapp.data.repositories.MovieRepository

class MovieInterceptor(private val repository: MovieRepository) {

    suspend fun getPopularMovies(page: Int) = repository.getPopularMovies(page)

    suspend fun getMovieDetails(id: Int) = repository.getMovieDetails(id)
}