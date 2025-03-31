package com.moviescatalog.features.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.moviescatalog.domain.model.Movie
import com.moviescatalog.domain.usecase.GetMovieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow<Movie?>(null)
    val uiState: StateFlow<Movie?> = _uiState

    fun loadMovie(movieId: Int) {
        viewModelScope.launch {
            val result = getMovieByIdUseCase.invoke(movieId)

            result.onSuccess { movie ->
                _uiState.value = movie
            }.onFailure {
                _uiState.value = null
            }
        }
    }
}