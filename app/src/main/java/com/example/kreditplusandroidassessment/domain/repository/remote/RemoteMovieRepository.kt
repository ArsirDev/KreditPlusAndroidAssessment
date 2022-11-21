package com.example.kreditplusandroidassessment.domain.repository.remote

import PopularResponse
import UpcomingResponse
import com.example.kreditplusandroidassessment.data.remote.dto.*
import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow
import com.example.kreditplusandroidassessment.util.Result

interface RemoteMovieRepository {

    fun getNowPlayingMovie(page: Int, language: String): Flow<Result<List<NowPlaying>>>

    fun getUpcomingMovie(page: Int, language: String): Flow<Result<List<Upcoming>>>

    fun getPopularMovie(page: Int, language: String): Flow<Result<List<Popular>>>

    fun getAllViewNowPlayingMovie(page: Int, language: String): Flow<Result<NowPlayingResponse>>

    fun getAllViewUpcomingMovie(page: Int, language: String): Flow<Result<UpcomingResponse>>

    fun getAllViewPopularMovie(page: Int, language: String): Flow<Result<PopularResponse>>

    fun getGenre(): Flow<Result<GenreResponse>>

    fun getDetailMovie(movie_id: Int, ): Flow<Result<DetailResponse>>

    fun getVideoMovie(movie_id: Int): Flow<Result<VideoResponse>>

    fun getSearchMovie(query: String, page: Int, language: String): Flow<Result<SearchMovieResponse>>

    fun getDiscoverMovie(with_genres: String, page: Int, include_adult: Boolean, language: String): Flow<Result<DiscoverMovieResponse>>
}