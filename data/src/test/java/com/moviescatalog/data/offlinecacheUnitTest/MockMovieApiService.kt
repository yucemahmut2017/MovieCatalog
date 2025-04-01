package com.moviescatalog.data.offlinecacheUnitTest

import com.moviescatalog.data.remote.api.MovieApiService
import com.moviescatalog.data.remote.dto.MovieDTO
import com.moviescatalog.data.remote.dto.MovieResponseDTO

class MockMovieApiService : MovieApiService {
    override suspend fun discoverMovies(sortBy: String, page: Int): MovieResponseDTO {
        return MovieResponseDTO(
            page = 1,
            results = listOf(
                MovieDTO(
                    id = 1,
                    title = "Inception",
                    overview = "A mind-bending thriller",
                    posterPath = "poster_url",
                    backdropPath = "backdrop_url",
                    voteAverage = 8.8,
                    releaseDate = "2010-07-16"
                )
            ),
            totalPages = 1,
            totalResults = 1
        )
    }

    override suspend fun getMovieById(movieId: Int): MovieDTO {
        return MovieDTO(
            id = movieId,
            title = "Inception",
            overview = "A mind-bending thriller",
            posterPath = "poster_url",
            backdropPath = "backdrop_url",
            voteAverage = 8.8,
            releaseDate = "2010-07-16"
        )
    }
}
