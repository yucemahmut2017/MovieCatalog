package com.moviescatalog.domain.repository

import com.moviescatalog.domain.model.Movie

interface MovieRepository {
    suspend fun getMovies(sortBy: String, page: Int): Result<List<Movie>>
    suspend fun getMovieById(movieId: Int): Result<Movie>
}