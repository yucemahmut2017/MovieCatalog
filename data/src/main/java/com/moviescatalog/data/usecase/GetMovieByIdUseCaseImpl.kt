package com.moviescatalog.data.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import com.moviescatalog.domain.usecase.GetMovieByIdUseCase
import javax.inject.Inject

class GetMovieByIdUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMovieByIdUseCase {
    override suspend fun invoke(movieId: Int): Result<Movie> {
        return repository.getMovieById(movieId)
    }
}