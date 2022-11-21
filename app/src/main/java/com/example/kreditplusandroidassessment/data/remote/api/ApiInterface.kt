package com.example.kreditplusandroidassessment.data.remote.api

import PopularResponse
import UpcomingResponse
import com.example.kreditplusandroidassessment.data.remote.dto.*
import com.example.kreditplusandroidassessment.util.MovieEndPoint.DETAIL_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.DISCOVER_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.GENRE_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.NOW_PLAYING_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.POPULAR_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.SEARCH_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.UPCOMING_MOVIE
import com.example.kreditplusandroidassessment.util.MovieEndPoint.VIDEO_MOVIE
import com.example.kreditplusandroidassessment.util.MovieKeyConstant.INCLUDE_ADULT
import com.example.kreditplusandroidassessment.util.MovieKeyConstant.KEY_LANGUAGE
import com.example.kreditplusandroidassessment.util.MovieKeyConstant.KEY_PAGE
import com.example.kreditplusandroidassessment.util.MovieKeyConstant.KEY_QUERY
import com.example.kreditplusandroidassessment.util.MovieKeyConstant.KEY_WITH_GENRE
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {

    @GET(NOW_PLAYING_MOVIE)
    suspend fun getNowPlayingMovie(
        @Query(KEY_PAGE) page: Int,
        @Query(KEY_LANGUAGE) language: String
    ): Response<NowPlayingResponse>

    @GET(UPCOMING_MOVIE)
    suspend fun getUpcomingMovies(
        @Query(KEY_PAGE) page: Int,
        @Query(KEY_LANGUAGE) language: String
    ): Response<UpcomingResponse>

    @GET(POPULAR_MOVIE)
    suspend fun getPopularMovies(
        @Query(KEY_PAGE) page: Int,
        @Query(KEY_LANGUAGE) language: String
    ): Response<PopularResponse>

    @GET(GENRE_MOVIE)
    suspend fun getGenreMovies(): Response<GenreResponse>

    @GET(DETAIL_MOVIE)
    suspend fun getDetailMovies(
        @Path("movie_id") movie_id: Int
    ): Response<DetailResponse>

    @GET(VIDEO_MOVIE)
    suspend fun getVideoMovies(
        @Path("movie_id") movie_id: Int
    ): Response<VideoResponse>

    @GET(SEARCH_MOVIE)
    suspend fun getSearchMovies(
        @Query(KEY_QUERY) query: String,
        @Query(KEY_PAGE) page: Int,
        @Query(INCLUDE_ADULT) include_adult: Boolean,
        @Query(KEY_LANGUAGE) language: String
    ): Response<SearchMovieResponse>

    @GET(DISCOVER_MOVIE)
    suspend fun getDiscoverMovies(
        @Query(KEY_WITH_GENRE) with_genres: String,
        @Query(KEY_PAGE) page: Int,
        @Query(INCLUDE_ADULT) include_adult: Boolean,
        @Query(KEY_LANGUAGE) language: String
    ): Response<DiscoverMovieResponse>
}