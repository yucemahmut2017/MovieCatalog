package com.moviescatalog.data.remote.dto


import com.moviescatalog.domain.model.Movie
import com.moviescatalog.core.util.Constants

fun MovieDTO.toDomainModel(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterPath?.takeIf { it.isNotBlank() }?.let { Constants.IMAGE_BASE_URL + it },
        backdropUrl = backdropPath?.takeIf { it.isNotBlank() }?.let { Constants.IMAGE_BASE_URL + it },
        rating = voteAverage,
        releaseDate = releaseDate
    )
}
