package com.example.kreditplusandroidassessment.presentation.home.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreditplusandroidassessment.domain.repository.remote.RemoteMovieRepository
import com.example.kreditplusandroidassessment.presentation.home.ui.home.State.HomeState
import com.example.kreditplusandroidassessment.util.Result
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus

class HomeViewModel(
    private val repository: RemoteMovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(HomeState())

    val state get() = _state.asStateFlow()

    private fun fetchMovie() = viewModelScope.launch {
        repository.getNowPlayingMovie(
            1,
            "en-US"
        ).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        nowPlaying = emptyList(),
                    )
                }
                is Result.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        nowPlaying = result.data
                    )
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        nowPlaying = emptyList(),
                    )
                }
            }
        }.launchIn(this + IO)

        repository.getUpcomingMovie(
            1,
            "en-US"
        ).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        upcoming = emptyList(),
                    )
                }
                is Result.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        upcoming = result.data
                    )
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        upcoming = emptyList(),
                    )
                }
            }
        }.launchIn(this + IO)

        repository.getPopularMovie(
            1,
            "en-US"
        ).onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true,
                        popular = emptyList()
                    )
                }
                is Result.Success -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        popular = result.data
                    )
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false,
                        popular = emptyList()
                    )
                }
            }
        }.launchIn(this + IO)
    }

    init {
        fetchMovie()
    }
}