package com.example.kreditplusandroidassessment.util

import com.example.kreditplusandroidassessment.data.local.MovieDatabase
import com.example.kreditplusandroidassessment.data.remote.api.ApiInterface
import com.example.kreditplusandroidassessment.data.repository.local.LocalMovieRepositoryImpl
import com.example.kreditplusandroidassessment.data.repository.remote.RemoteMovieRepositoryImpl
import com.example.kreditplusandroidassessment.domain.adapter.nowplaying.NowPlayingAdapter
import com.example.kreditplusandroidassessment.domain.adapter.popular.PopularAdapter
import com.example.kreditplusandroidassessment.domain.adapter.slider.SliderAdapter
import com.example.kreditplusandroidassessment.domain.adapter.upcoming.UpcomingAdapter
import com.example.kreditplusandroidassessment.domain.repository.local.LocalMovieRepository
import com.example.kreditplusandroidassessment.domain.repository.remote.RemoteMovieRepository
import com.example.kreditplusandroidassessment.presentation.detail.viewmodel.DetailMovieViewModel
import com.example.kreditplusandroidassessment.presentation.home.ui.home.viewmodel.HomeViewModel
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.popular.ViewAllPopularAdapter
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.adapter.upcoming.ViewAllUpcomingAdapter
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.viewmodel.popular.ViewAllPopularViewModel
import com.example.kreditplusandroidassessment.presentation.home.ui.viewall.viewmodel.upcoming.ViewAllUpcomingViewModel
import com.example.kreditplusandroidassessment.presentation.sliderscreen.viewmodel.SliderViewModel
import com.example.kreditplusandroidassessment.presentation.splashscreen.viewmodel.SplashViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val local = module {
    single<LocalMovieRepository> { LocalMovieRepositoryImpl(sessionManager = get()) }
    single { ServiceGenerate.createService(serviceClass = ApiInterface::class.java) }
    single { MovieDatabase.invoke(context = get()) }
    single { SessionManager(context = get()) }
}

val remote = module {
    single<RemoteMovieRepository> { RemoteMovieRepositoryImpl(service = get(), database = get()) }
}

val splash = module {
    viewModel { SplashViewModel(repository = get()) }
}

val slider = module {
    viewModel { SliderViewModel(repository = get()) }
    factory { SliderAdapter() }
}

val home = module {
    viewModel { HomeViewModel(repository = get()) }
    factory { NowPlayingAdapter() }
    factory { UpcomingAdapter() }
    factory { PopularAdapter() }
}

val detail = module {
    viewModel { DetailMovieViewModel(repository = get()) }
}

val viewAll = module {
    single { ViewAllUpcomingViewModel(repository = get()) }
    single { ViewAllPopularViewModel(repository = get()) }
    factory { ViewAllUpcomingAdapter() }
    factory { ViewAllPopularAdapter() }
}