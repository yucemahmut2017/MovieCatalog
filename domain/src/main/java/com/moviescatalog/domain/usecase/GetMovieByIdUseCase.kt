package com.moviescatalog.domain.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import javax.inject.Inject


interface GetMovieByIdUseCase {
    suspend operator fun invoke(movieId: Int): Result<Movie>
}
class GetMovieByIdUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMovieByIdUseCase {

    override suspend fun invoke(movieId: Int): Result<Movie> {
        return repository.getMovieById(movieId)
    }
}