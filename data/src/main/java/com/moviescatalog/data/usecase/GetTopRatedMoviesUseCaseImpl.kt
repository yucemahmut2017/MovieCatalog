package com.moviescatalog.data.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import com.moviescatalog.domain.usecase.GetTopRatedMoviesUseCase
import javax.inject.Inject

class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetTopRatedMoviesUseCase {
    override suspend fun invoke(page: Int): Result<List<Movie>>{
        return repository.getMovies("vote_average.desc", page)
    }
}