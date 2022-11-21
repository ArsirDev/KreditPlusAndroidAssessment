package com.example.kreditplusandroidassessment.domain.repository.remote

import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow
import com.example.kreditplusandroidassessment.util.Result

interface RemoteMovieRepository {

    fun getNowPlayingMovie(page: Int, language: String): Flow<Result<List<NowPlaying>>>

    fun getUpcomingMovie(page: Int, language: String): Flow<Result<List<Upcoming>>>

    fun getPopularMovie(page: Int, language: String): Flow<Result<List<Popular>>>

    fun getAllViewUpcomingMovie(page: Int, language: String): Flow<Result<Upcoming>>

    fun getAllViewPopularMovie(page: Int, language: String): Flow<Result<Popular>>
}