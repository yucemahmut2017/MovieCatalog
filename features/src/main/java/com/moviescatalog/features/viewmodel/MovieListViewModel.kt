package com.moviescatalog.features.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.model.MovieCategory
import com.moviescatalog.domain.usecase.GetMoviesByReleaseDateUseCase
import com.moviescatalog.domain.usecase.GetPopularMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRatedMoviesUseCase
import com.moviescatalog.domain.usecase.GetTopRevenueMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
    private val getTopRevenueMoviesUseCase: GetTopRevenueMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getMoviesByReleaseDateUseCase: GetMoviesByReleaseDateUseCase,
) : ViewModel() {

    // Global loading state to indicate initial data fetching
    val isLoading = MutableStateFlow(true)

    // Map to keep track of the current page number for each movie category
    private val _pageByCategory = mutableMapOf(
        MovieCategory.POPULAR to 1,
        MovieCategory.REVENUE to 1,
        MovieCategory.TOP_RATED to 1,
        MovieCategory.RELEASE_DATE to 1,
    )

    // Map to manage the loading state for each movie category
    private val _isLoadingByCategory = mutableMapOf(
        MovieCategory.POPULAR to MutableStateFlow(false),
        MovieCategory.REVENUE to MutableStateFlow(false),
        MovieCategory.TOP_RATED to MutableStateFlow(false),
        MovieCategory.RELEASE_DATE to MutableStateFlow(false),
    )

    // Publicly exposed immutable map of loading states
    val isLoadingByCategory: Map<MovieCategory, StateFlow<Boolean>> = _isLoadingByCategory

    // Map to store the list of movies for each category
    private val _moviesByCategory = mutableMapOf(
        MovieCategory.POPULAR to MutableStateFlow(emptyList<Movie>()),
        MovieCategory.REVENUE to MutableStateFlow(emptyList()),
        MovieCategory.TOP_RATED to MutableStateFlow(emptyList()),
        MovieCategory.RELEASE_DATE to MutableStateFlow(emptyList()),

    )

    // Publicly exposed immutable map of movie lists
    val moviesByCategory: Map<MovieCategory, StateFlow<List<Movie>>> = _moviesByCategory

    init {
        // Fetch initial data for all categories
        fetchAllInitial()
    }

    /**
     * Fetches the initial set of movies for all categories.
     * Sets the global loading state to true during the fetch.
     */
    private fun fetchAllInitial() {
        viewModelScope.launch {
            isLoading.value = true
            MovieCategory.entries.forEach { category ->
                fetchNextPage(category)
            }
            isLoading.value = false
        }
    }

    /**
     * Fetches the next page of movies for the specified category.
     * Prevents duplicate fetches by checking the loading state.
     *
     * @param category The movie category for which to fetch the next page.
     */
    fun fetchNextPage(category: MovieCategory) {
        val isLoadingFlow = _isLoadingByCategory[category] ?: return
        if (isLoadingFlow.value) return

        val currentPage = _pageByCategory[category] ?: 1
        isLoadingFlow.value = true

        viewModelScope.launch {
            val result = when (category) {
                MovieCategory.POPULAR -> getPopularMoviesUseCase(currentPage)
                MovieCategory.REVENUE -> getTopRevenueMoviesUseCase(currentPage)
                MovieCategory.TOP_RATED -> getTopRatedMoviesUseCase(currentPage)
                MovieCategory.RELEASE_DATE -> getMoviesByReleaseDateUseCase(currentPage)
            }

            result.onSuccess { newMovies ->
                val currentList = _moviesByCategory[category]?.value ?: emptyList()
                _moviesByCategory[category]?.value = currentList + newMovies
                _pageByCategory[category] = currentPage + 1
            }.onFailure {
                // Log error if necessary
            }

            isLoadingFlow.value = false
        }
    }
}


