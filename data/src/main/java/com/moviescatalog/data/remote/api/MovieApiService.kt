package com.moviescatalog.data.remote.api


import com.moviescatalog.data.remote.dto.MovieDTO
import com.moviescatalog.data.remote.dto.MovieResponseDTO
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("discover/movie")
    suspend fun discoverMovies(
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
    ): MovieResponseDTO

    @GET("movie/{movie_id}")
    suspend fun getMovieById(
        @Path("movie_id") movieId: Int,
    ): MovieDTO
}