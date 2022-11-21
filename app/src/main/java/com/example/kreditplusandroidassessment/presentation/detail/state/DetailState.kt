package com.example.kreditplusandroidassessment.presentation.detail.state

import com.example.kreditplusandroidassessment.data.remote.dto.DetailResponse
import com.example.kreditplusandroidassessment.data.remote.dto.VideoResultsItem

data class DetailState(
    val detail: DetailResponse? = null,
    val video: List<VideoResultsItem>? = emptyList(),
    val isLoading: Boolean = false
)
