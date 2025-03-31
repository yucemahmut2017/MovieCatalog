package com.moviescatalog.data.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import com.moviescatalog.domain.usecase.GetTopRatedMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRevenueMoviesUseCase
import javax.inject.Inject

class GetTopRevenueMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetTopRevenueMoviesUseCase {
    override suspend fun invoke(page: Int): Result<List<Movie>>{
        return repository.getMovies("vote_average.desc", page)
    }
}