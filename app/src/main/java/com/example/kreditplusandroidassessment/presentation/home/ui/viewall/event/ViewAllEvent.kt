package com.example.kreditplusandroidassessment.presentation.home.ui.viewall.event

sealed class ViewAllEvent {
    object LoadMore : ViewAllEvent()
    object DefaultPage: ViewAllEvent()
}
