package com.moviescatalog.data.local.dto

import com.moviescatalog.data.local.MovieEntity
import com.moviescatalog.domain.model.Movie

fun MovieEntity.toDomainModel(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterUrl = posterUrl,
        backdropUrl = backdropUrl,
        rating = rating,
        releaseDate = releaseDate
    )
}

