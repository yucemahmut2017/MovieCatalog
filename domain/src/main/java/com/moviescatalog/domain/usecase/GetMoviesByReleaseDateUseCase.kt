package com.moviescatalog.domain.usecase

import com.moviescatalog.domain.model.Movie

interface GetMoviesByReleaseDateUseCase {
    suspend operator fun invoke(page: Int): Result<List<Movie>>
}
