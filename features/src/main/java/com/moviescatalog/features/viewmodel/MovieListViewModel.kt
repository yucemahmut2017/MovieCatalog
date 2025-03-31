package com.moviescatalog.features.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.model.MovieCategory
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
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase
) : ViewModel() {

    private val _moviesByCategory = mutableMapOf<MovieCategory, MutableStateFlow<List<Movie>>>(
        MovieCategory.POPULAR to MutableStateFlow(emptyList()),
        MovieCategory.REVENUE to MutableStateFlow(emptyList()),
        MovieCategory.TOP_RATED to MutableStateFlow(emptyList())
    )

    val moviesByCategory: Map<MovieCategory, StateFlow<List<Movie>>> = _moviesByCategory

    init {
        fetchAll()
    }

    private fun fetchAll(page: Int = 1) {
        MovieCategory.entries.forEach { category ->
            fetchMoviesByCategory(category, page)
        }
    }

    private fun fetchMoviesByCategory(category: MovieCategory, page: Int) {
        viewModelScope.launch {
            val result = when (category) {
                MovieCategory.POPULAR -> getPopularMoviesUseCase(page)
                MovieCategory.REVENUE -> getTopRevenueMoviesUseCase(page)
                MovieCategory.TOP_RATED -> getTopRatedMoviesUseCase(page)
            }

            result.onSuccess {
                _moviesByCategory[category]?.value = it
            }.onFailure {
                _moviesByCategory[category]?.value = emptyList()
            }
        }
    }
}