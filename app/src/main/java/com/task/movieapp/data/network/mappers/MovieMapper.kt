package com.task.movieapp.data.network.mappers

import android.util.Log
import com.task.movieapp.data.network.response.movie.DetailsResp
import com.task.movieapp.data.network.response.movie.MovieResp
import com.task.movieapp.domain.movie.model.Movie

object MovieMapper {

    private const val imageStorage = "https://image.tmdb.org/t/p/w500"

    private fun movieFromGeneralResponse(movieResp: MovieResp): Movie? {
        Log.d("asasasas5550", "movieResp.voteAverage: " + movieResp.voteAverage)

        return if (movieResp.id != null && movieResp.title != null) {
            Movie(
                id = movieResp.id,
                title = movieResp.title,
                adult = movieResp.adult ?: false,
                backdropPath = movieResp.backdropPath?.let { imageStorage + it }
                    ?: "https://region.center/source/TULA/2020/6/cinema.jpg",
                genres = arrayListOf(),
                originalLanguage = movieResp.originalLanguage,
                originalTitle = movieResp.originalTitle,
                overview = movieResp.overview,
                posterPath = movieResp.posterPath?.let { imageStorage + it }
                    ?: "https://images-na.ssl-images-amazon.com/images/I/61vuT+YGXyL.jpg",
                releaseDate = movieResp.releaseDate,
                rating = movieResp.voteAverage,
                voteCount = movieResp.voteCount,
            )
        } else {
            null
        }
    }

    fun moviesFromResponse(movieRespList: List<MovieResp>): List<Movie> {
        val movies = arrayListOf<Movie>()
        movieRespList.forEach { response ->
            movieFromGeneralResponse(response)?.let {
                movies.add(it)
            }
        }
        return movies
    }

    fun movieFromDetailsResponse(detailsResp: DetailsResp): Movie? {
        return if (detailsResp.id != null && detailsResp.title != null) {
            Movie(
                id = detailsResp.id,
                title = detailsResp.title,
                adult = detailsResp.adult ?: false,
                backdropPath = detailsResp.backdropPath?.let { imageStorage + it }
                    ?: "https://region.center/source/TULA/2020/6/cinema.jpg",
                genres = detailsResp.genres?.map { it.name ?: "" } ?: arrayListOf(),
                originalLanguage = detailsResp.originalLanguage,
                originalTitle = detailsResp.originalTitle,
                overview = detailsResp.overview,
                posterPath = detailsResp.backdropPath?.let { imageStorage + it }
                    ?: "https://images-na.ssl-images-amazon.com/images/I/61vuT+YGXyL.jpg",
                releaseDate = detailsResp.releaseDate,
                rating = detailsResp.voteAverage,
                voteCount = detailsResp.voteCount,
                budget = detailsResp.budget,
                productionCompanies = detailsResp.productionCompanies?.map { it.name ?: "" },
                productionCountries = detailsResp.productionCountries?.map { it.name ?: "" },
                revenue = detailsResp.revenue,
                spokenLanguages = detailsResp.spokenLanguages?.map { it.englishName ?: "" },
                status = detailsResp.status,
                tagLine = detailsResp.tagLine,
                duration = detailsResp.runtime
            )
        } else {
            null
        }
    }
}