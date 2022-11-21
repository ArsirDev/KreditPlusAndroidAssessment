package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.state

import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.domain.model.Upcoming

data class ViewAllState(
    val upcoming: List<Upcoming> = emptyList(),
    val popular: List<Popular> = emptyList(),
    val allowLoadNext: Boolean = false,
    val page: Int = 1,
    val isLoading: Boolean = false
)