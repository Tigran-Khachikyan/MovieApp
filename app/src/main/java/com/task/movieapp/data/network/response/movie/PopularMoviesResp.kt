package com.task.movieapp.data.network.response.movie

import com.google.gson.annotations.SerializedName

data class PopularMoviesResp(

    @SerializedName("page")
    val page: Int?,

    @SerializedName( "results")
    val results: List<MovieResp>?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    @SerializedName("total_results")
    val totalResults: Int?
)