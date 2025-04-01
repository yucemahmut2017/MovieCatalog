package com.moviescatalog.data.repository


import com.moviescatalog.data.local.MovieDao
import com.moviescatalog.data.local.dto.toDomainModel
import com.moviescatalog.data.local.dto.toEntity
import com.moviescatalog.data.remote.api.MovieApiService
import com.moviescatalog.data.remote.dto.toDomainModel
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.model.MovieCategory
import com.moviescatalog.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api: MovieApiService,
    private val movieDao: MovieDao
) : MovieRepository {

    override suspend fun getMovies(sortBy: String, page: Int): Result<List<Movie>> {
        return try {
            // 1. Try to get movies from the database
            val cachedMovies = movieDao.getMoviesByCategory(sortBy)
            if (cachedMovies.isNotEmpty()) {
                return Result.success(cachedMovies.map { it.toDomainModel() })
            }

            // 2. If cache is empty, fetch from the API
            val response = api.discoverMovies(sortBy, page)
            val movies = response.results.map { it.toEntity(sortBy) }
            movieDao.insertMovies(movies)

            Result.success(movies.map { it.toDomainModel() }) // Return as domain model
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieById(movieId: Int): Result<Movie> {
        return try {
            val cachedMovie = movieDao.getMovieById(movieId)
            if (cachedMovie != null) {
                // Return cached movie if exists
                return Result.success(cachedMovie.toDomainModel())
            }

            val response = api.getMovieById(movieId)

            movieDao.insertMovie(response.toEntity(MovieCategory.POPULAR.name))

            Result.success(response.toDomainModel())
        } catch (e: Exception) {
            Result.failure(e)
        }
    }


}


