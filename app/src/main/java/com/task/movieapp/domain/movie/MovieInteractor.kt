package com.task.movieapp.domain.movie

import com.task.movieapp.data.repositories.MovieRepository
import com.task.movieapp.domain.movie.model.Movie
import com.task.movieapp.utils.Request
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class MovieInteractor(private val repository: MovieRepository) {

    suspend fun getPopularMovies(page: Int): Flow<Request<List<Movie>>> =
        withContext(Dispatchers.IO) { repository.getPopularMovies(page) }

    suspend fun getMovieDetails(id: Int): Flow<Request<Movie>> =
        withContext(Dispatchers.IO) { repository.getMovieDetails(id) }
}