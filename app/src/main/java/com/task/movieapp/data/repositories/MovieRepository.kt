package com.task.movieapp.data.repositories

import com.task.movieapp.data.db.AppDatabase
import com.task.movieapp.data.db.mappers.MovieMapperDb
import com.task.movieapp.utils.Request
import com.task.movieapp.data.network.MovieApi
import com.task.movieapp.data.network.mappers.MovieMapper
import com.task.movieapp.domain.movie.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MovieRepository(
    private val api: MovieApi,
    database: AppDatabase
) {

    private val dao by lazy { database.movieDao }

    suspend fun getPopularMovies(page: Int): Flow<Request<List<Movie>>> = flow {
        try {
            emit(Request.Loading)
            val response = api.getPopularMovies(page = page)
            if (response.isSuccessful) {
                val info = response.body()?.results?.let { MovieMapper.moviesFromResponse(it) }
                if (info != null) {
                    emit(Request.Success.FromNetwork(info))
                    dao.saveAllMovies(MovieMapperDb.toEntities(info))
                } else {
                    emit(Request.Error.EmptyResponse)
                    dao.getPopularMovies()?.let {
                        val cached = MovieMapperDb.fromEntities(it)
                        emit(Request.Success.FromCache(cached))
                    } ?: emit(Request.Error.EmptyCache)
                }
            } else {
                emit(Request.Error.ResponseNotSucceed)
                dao.getPopularMovies()?.let {
                    val cached = MovieMapperDb.fromEntities(it)
                    emit(Request.Success.FromCache(cached))
                } ?: emit(Request.Error.EmptyCache)
            }
        } catch (ex: Exception) {
            emit(Request.Error.ConnectionError)
            dao.getPopularMovies()?.let {
                val cached = MovieMapperDb.fromEntities(it)
                emit(Request.Success.FromCache(cached))
            } ?: emit(Request.Error.EmptyCache)
        }
    }

    suspend fun getMovieDetails(id: Int): Flow<Request<Movie>> = flow {
        try {
            emit(Request.Loading)
            val response = api.getDetails(id)
            if (response.isSuccessful) {
                val info =
                    response.body()?.let { MovieMapper.movieFromDetailsResponse(it) }
                if (info != null) {
                    dao.saveMovieDetails(MovieMapperDb.toEntity(info))
                    emit(Request.Success.FromNetwork(info))
                } else {
                    emit(Request.Error.EmptyResponse)
                    dao.getMovieDetails(id)?.let {
                        val cached = MovieMapperDb.fromEntity(it)
                        emit(Request.Success.FromCache(cached))
                    } ?: emit(Request.Error.EmptyCache)
                }
            } else {
                emit(Request.Error.ResponseNotSucceed)
                dao.getMovieDetails(id)?.let {
                    val cached = MovieMapperDb.fromEntity(it)
                    emit(Request.Success.FromCache(cached))
                } ?: emit(Request.Error.EmptyCache)
            }
        } catch (ex: Exception) {
            emit(Request.Error.ConnectionError)
            dao.getMovieDetails(id)?.let {
                val cached = MovieMapperDb.fromEntity(it)
                emit(Request.Success.FromCache(cached))
            } ?: emit(Request.Error.EmptyCache)
        }
    }

}