package com.moviescatalog.data.local.dto

import com.moviescatalog.core.util.Constants
import com.moviescatalog.data.local.MovieEntity
import com.moviescatalog.data.remote.dto.MovieDTO
import com.moviescatalog.data.remote.dto.MovieResponseDTO

fun MovieDTO.toEntity(category: String): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterPath?.let { Constants.IMAGE_BASE_URL + it },
        backdropUrl = backdropPath?.let { Constants.IMAGE_BASE_URL + it },
        rating = voteAverage,
        releaseDate = releaseDate,
        category = category
    )
}


