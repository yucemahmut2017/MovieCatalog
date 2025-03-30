package com.moviescatalog.domain.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import javax.inject.Inject



interface GetTopRatedMoviesUseCase {
    suspend operator fun invoke(page: Int): Result<List<Movie>>
}
class GetTopRatedMoviesUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetTopRatedMoviesUseCase {
    override suspend fun invoke(page: Int): Result<List<Movie>>{
        return repository.getMovies("vote_average.desc", page)
    }
}