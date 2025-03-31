package com.moviescatalog.data.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import com.moviescatalog.domain.usecase.GetPopularMoviesUseCase
import javax.inject.Inject

class GetPopularMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetPopularMoviesUseCase {

    override suspend fun invoke(page: Int): Result<List<Movie>> {
        return repository.getMovies("popularity.desc", page)
    }
}