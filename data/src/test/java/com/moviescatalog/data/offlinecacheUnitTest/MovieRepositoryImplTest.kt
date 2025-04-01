package com.moviescatalog.data.offlinecacheUnitTest

import android.util.Log
import com.google.common.truth.Truth.assertThat
import com.moviescatalog.data.local.MovieDao
import com.moviescatalog.data.remote.api.MovieApiService
import com.moviescatalog.data.remote.dto.MovieDTO
import com.moviescatalog.data.remote.dto.MovieResponseDTO
import com.moviescatalog.data.repository.MovieRepositoryImpl
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import kotlin.test.Test


@OptIn(ExperimentalCoroutinesApi::class)
class MovieRepositoryImplTest {

    @get:Rule
    val mainCoroutineRule = MainCoroutineRule()

    private lateinit var movieRepository: MovieRepositoryImpl
    private lateinit var movieDao: MovieDao
    private lateinit var mockApiService: MovieApiService

    @Before
    fun setUp() {
        mockApiService = mock(MovieApiService::class.java)
        movieDao = mock(MovieDao::class.java)
        movieRepository = MovieRepositoryImpl(mockApiService, movieDao)
    }

    @Test
    fun `test repository fetches from API and stores in database`() = runBlocking {
        // Mock API response
        val mockResponse = MovieResponseDTO(
            page = 1,
            results = listOf(MovieDTO(id = 1, title = "Movie 1", overview = "Overview", posterPath = "", backdropPath = "", voteAverage = 5.0, releaseDate = "2021-01-01")),
            totalPages = 1,
            totalResults = 1
        )
        Log.d("MovieRepositoryTest", "Mock API Response: $mockResponse")

        `when`(mockApiService.discoverMovies("popular", 1)).thenReturn(mockResponse)

        val result = movieRepository.getMovies("popular", 1)

        verify(mockApiService).discoverMovies("popular", 1)
        verify(movieDao).insertMovies(anyList())

        assertThat(result.isSuccess).isTrue()
    }
}


