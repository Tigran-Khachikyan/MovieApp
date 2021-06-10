package com.task.movieapp.data.db.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MOVIE_INFO")
data class MovieDb(


    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "ID")
    val id: Int,

    @ColumnInfo(name = "TITLE")
    val title: String,

    @ColumnInfo(name = "FOR_ADULT")
    val adult: Boolean,

    @ColumnInfo(name = "BACKGROUND_PATH")
    val backdropPath: String,

    @ColumnInfo(name = "GENRES")
    val genres: List<String>,

    @ColumnInfo(name = "ORIG_LANGUAGES")
    val originalLanguage: String?,

    @ColumnInfo(name = "ORIG_TITLE")
    val originalTitle: String?,

    @ColumnInfo(name = "OVERVIEW")
    val overview: String?,

    @ColumnInfo(name = "POSTER_PATH")
    val posterPath: String,

    @ColumnInfo(name = "RELEASE_DATE")
    val releaseDate: String?,

    @ColumnInfo(name = "RATING")
    val rating: Double?,

    @ColumnInfo(name = "VOTE_COUNT")
    val voteCount: Int?,

    @ColumnInfo(name = "BUDGET")
    val budget: Int?,

    @ColumnInfo(name = "PROD_COMPANIES")
    val productionCompanies: List<String>?,

    @ColumnInfo(name = "PROD_COUNTRIES")
    val productionCountries: List<String>?,

    @ColumnInfo(name = "REVENUE")
    val revenue: Int?,

    @ColumnInfo(name = "SPOKEN_LANGUAGES")
    val spokenLanguages: List<String>?,

    @ColumnInfo(name = "STATUS")
    val status: String?,

    @ColumnInfo(name = "TAG_LINE")
    val tagLine: String?,

    @ColumnInfo(name = "DURATION")
    val duration: Int?
)