package com.task.movieapp.data.network.response.movie

import com.google.gson.annotations.SerializedName

data class ProductionCompany(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("logo_path")
    val logo_path: Any?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
)