package com.example.kreditplusandroidassessment.presentation.home.ui.home.state

import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.domain.model.Upcoming

data class HomeState(
    val isLoading: Boolean = false,
    val nowPlaying: List<NowPlaying>? = emptyList(),
    val upcoming: List<Upcoming>? = emptyList(),
    val popular: List<Popular>? = emptyList()
)