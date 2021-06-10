package com.task.movieapp.domain.movie.model

data class Movie(

    val id: Int,
    val title: String,
    val adult: Boolean,
    val backdropPath: String,
    val genres: List<String>,
    val originalLanguage: String?,
    val originalTitle: String?,
    val overview: String?,
    val posterPath: String,
    val releaseDate: String?,
    val rating: Double?,
    val voteCount: Int?,
    val budget: Int? = null,
    val productionCompanies: List<String>? = null,
    val productionCountries: List<String>? = null,
    val revenue: Int? = null,
    val spokenLanguages: List<String>? = null,
    val status: String? = null,
    val tagLine: String? = null,
    val duration: Int? = null
)