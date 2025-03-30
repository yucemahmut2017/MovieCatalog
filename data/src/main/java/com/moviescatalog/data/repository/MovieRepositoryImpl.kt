package com.moviescatalog.data.repository


import com.moviescatalog.data.remote.api.MovieApiService
import com.moviescatalog.data.remote.dto.toDomainModel
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApiService
) : MovieRepository {

    override suspend fun getMovies(sortBy: String, page: Int): Result<List<Movie>> {
        return try {
            val response = api.discoverMovies(sortBy, page)
            val movies = response.results.map { it.toDomainModel() }
            Result.success(movies)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieById(movieId: Int): Result<Movie> {
        return try {
            val response = api.getMovieById(movieId)
            Result.success(response.toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

}
