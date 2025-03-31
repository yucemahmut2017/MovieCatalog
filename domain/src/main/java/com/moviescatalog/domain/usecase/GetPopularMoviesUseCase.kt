package com.moviescatalog.domain.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import javax.inject.Inject

interface GetPopularMoviesUseCase {
    suspend operator fun invoke(page: Int): Result<List<Movie>>
}
