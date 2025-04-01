package com.moviescatalog.data.usecase

import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import com.moviescatalog.domain.usecase.GetMoviesByReleaseDateUseCase
import com.moviescatalog.domain.usecase.GetPopularMoviesUseCase
import javax.inject.Inject

class GetMoviesByReleaseDateUseCaseImpl @Inject constructor(
    private val repository: MovieRepository
) : GetMoviesByReleaseDateUseCase {

    override suspend fun invoke(page: Int): Result<List<Movie>> {
        return repository.getMovies("release_date.desc", page)
    }
}