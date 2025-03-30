package com.moviescatalog.data.remote.dto

import com.google.gson.annotations.SerializedName

data class MovieResponseDTO(
    val page: Int,
    val results: List<MovieDTO>,
    @SerializedName("total_pages") val totalPages: Int,
    @SerializedName("total_results") val totalResults: Int
)