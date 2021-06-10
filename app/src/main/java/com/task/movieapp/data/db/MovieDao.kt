package com.task.movieapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.movieapp.data.db.entities.MovieDb
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveAllMovies(list: List<MovieDb>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieDetails(movieDb: MovieDb)

    @Query("SELECT * FROM MOVIE_INFO")
    suspend fun getPopularMovies(): List<MovieDb>?

    @Query("SELECT * FROM MOVIE_INFO WHERE ID == :id")
    suspend fun getMovieDetails(id: Int): MovieDb?
}