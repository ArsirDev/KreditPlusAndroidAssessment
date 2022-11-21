package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.viewmodel.popular

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kreditplusandroidassessment.domain.repository.remote.RemoteMovieRepository
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.event.ViewAllEvent
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.state.ViewAllState
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.plus
import com.example.kreditplusandroidassessment.util.Result

class ViewAllPopularViewModel (
    private val repository: RemoteMovieRepository
): ViewModel() {

    private val _state = MutableStateFlow(ViewAllState())

    val state get() = _state.asStateFlow()

    fun onEvent(event: ViewAllEvent) {
        when(event) {
            ViewAllEvent.DefaultPage ->  {
                getPopular(state.value.page)
            }
            ViewAllEvent.LoadMore ->  {
                if (state.value.allowLoadNext) {
                    getPopular(state.value.page + 1)
                }
            }
        }
    }

    private fun getPopular(
        page: Int
    ) {
        repository.getAllViewPopularMovie(page, "en-US").onEach { result ->
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
                            popular = if (page == 1) {
                                result.data?.results ?: emptyList()
                            } else {
                                state.value.popular + (result.data?.results  ?: emptyList())
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
        }.launchIn(viewModelScope + IO)
    }
}