package com.task.movieapp.data.network

import com.task.movieapp.data.network.response.movie.DetailsResp
import com.task.movieapp.data.network.response.movie.PopularMoviesResp
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    // https://api.themoviedb.org/3/movie/popular?api_key=<<api_key>>&language=en-US&page=1
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ): Response<PopularMoviesResp>


    // https://api.themoviedb.org/3/movie/15?api_key=<<api_key>>&language=en-US
    @GET("movie/{movie_id}")
    suspend fun getDetails(
        @Path("movie_id") id: Int,
        @Query("language") language: String = "en",
        @Query("page") page: Int = 1
    ): Response<DetailsResp>
}
