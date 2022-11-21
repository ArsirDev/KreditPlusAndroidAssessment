package com.example.kreditplusandroidassessment.presentation.detail.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreditplusandroidassessment.domain.repository.remote.RemoteMovieRepository
import com.example.kreditplusandroidassessment.presentation.detail.state.DetailState
import com.example.kreditplusandroidassessment.util.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus

class DetailMovieViewModel(
    private val repository: RemoteMovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(DetailState())

    val state get() = _state.asStateFlow()

    fun getDetail(
        movie_id: Int
    ) {
        repository.getDetailMovie(movie_id).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            detail = result.data
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }

    fun getVideoMovie(
        movie_id: Int
    ) {
        repository.getVideoMovie(movie_id).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
                is Result.Success -> {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            video = result.data?.results
                        )
                    }
                }
                is Result.Error -> {
                    _state.update {
                        it.copy(
                            isLoading = false
                        )
                    }
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }
}