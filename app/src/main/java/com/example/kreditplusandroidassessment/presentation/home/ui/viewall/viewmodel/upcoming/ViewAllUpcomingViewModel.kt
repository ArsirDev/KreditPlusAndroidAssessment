package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.viewmodel.upcoming

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreditplusandroidassessment.domain.repository.remote.RemoteMovieRepository
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.event.ViewAllEvent
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.state.ViewAllState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import com.example.kreditplusandroidassessment.util.Result

class ViewAllUpcomingViewModel(
    private val repository: RemoteMovieRepository
): ViewModel() {
    private val _state = MutableStateFlow(ViewAllState())

    val state get() = _state.asStateFlow()

    fun onEvent(event: ViewAllEvent) {
        when(event) {
            ViewAllEvent.DefaultPage -> {
                getUpcoming(state.value.page)
            }
            ViewAllEvent.LoadMore -> {
                if (state.value.allowLoadNext) {
                    getUpcoming(state.value.page + 1)
                }
            }
        }
    }

    private fun getUpcoming(
        page: Int
    ) {
        repository.getAllViewUpcomingMovie(page, "en-US").onEach { result ->
            when(result) {
                is Result.Loading -> {
                    _state.value = state.value.copy(
                        isLoading = true
                    )
                }
                is Result.Success -> {
                    val allow = page < 1000
                    _state.update { get ->
                        get.copy(
                            isLoading = false,
                            allowLoadNext = allow,
                            page = page,
                            upcoming = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.upcoming + (result.data?.results  ?: emptyList())
                            }
                        )
                    }
                }
                is Result.Error -> {
                    _state.value = state.value.copy(
                        isLoading = false
                    )
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }

}