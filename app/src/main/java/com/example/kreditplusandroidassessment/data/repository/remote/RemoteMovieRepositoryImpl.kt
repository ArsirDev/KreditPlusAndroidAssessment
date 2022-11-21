package com.example.kreditplusandroidassessment.data.repository.remote

import PopularResponse
import UpcomingResponse
import android.util.Log
import androidx.room.withTransaction
import com.example.kreditplusandroidassessment.data.local.MovieDatabase
import com.example.kreditplusandroidassessment.data.remote.api.ApiInterface
import com.example.kreditplusandroidassessment.data.remote.dto.*
import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import com.example.kreditplusandroidassessment.domain.repository.remote.RemoteMovieRepository
import com.example.kreditplusandroidassessment.util.Result
import com.example.kreditplusandroidassessment.util.serviceBindingResource
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onEach

class RemoteMovieRepositoryImpl(
    private val service: ApiInterface,
    private val database: MovieDatabase
): RemoteMovieRepository {
    override fun getNowPlayingMovie(page: Int, language: String): Flow<Result<List<NowPlaying>>> = serviceBindingResource(
        query = {
            database.nowPlayingDao().getAllNowPlaying()
        },
        fetch = {
            delay(1000)
            service.getNowPlayingMovie(page, language)
        },
        saveFetchResult = { nowPlaying ->
            database.withTransaction {
                database.nowPlayingDao().deleteAllNowPlaying()
                nowPlaying.body()?.nowPlayingMovieList()?.let { nowPlayingList ->
                    database.nowPlayingDao().insertNowPlaying(nowPlayingList)
                }
            }
        }
    )

    override fun getUpcomingMovie(page: Int, language: String): Flow<Result<List<Upcoming>>> = serviceBindingResource(
        query = {
            database.upcomingDao().getUpcomingMovie()
        },
        fetch = {
            delay(1000)
            service.getUpcomingMovies(page, language)
        },
        saveFetchResult = { result ->
            database.withTransaction {
                database.upcomingDao().deleteAllUpcomingMovie()
                result.body()?.toUpcomingList()?.let { upcoming ->
                    database.upcomingDao().insertUpcomingMovie(upcoming)
                }
            }
        }
    )

    override fun getPopularMovie(page: Int, language: String): Flow<Result<List<Popular>>> = serviceBindingResource(
         query =  {
             database.popularDao().getAllPopularMovie()
         },
        fetch =  {
            delay(1000)
            service.getPopularMovies(page, language)
        },
        saveFetchResult = { result ->
            database.withTransaction {
                database.popularDao().deleteAllPopularMovie()
                result.body()?.toPopularList()?.let { popular ->
                    database.popularDao().insertPopularMovie(popular)
                }
            }
        }
    )

    override fun getAllViewNowPlayingMovie(
        page: Int,
        language: String
    ): Flow<Result<NowPlayingResponse>> = flow {
        emit(Result.Loading())

        val result = service.getNowPlayingMovie(page, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getAllViewUpcomingMovie(page: Int, language: String): Flow<Result<UpcomingResponse>> = flow {
        emit(Result.Loading())

        val result = service.getUpcomingMovies(page, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getAllViewPopularMovie(page: Int, language: String): Flow<Result<PopularResponse>> = flow {
        emit(Result.Loading())

        val result = service.getPopularMovies(page, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getGenre(): Flow<Result<GenreResponse>> = flow {
        emit(Result.Loading())

        val result = service.getGenreMovies()

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getDetailMovie(movie_id: Int): Flow<Result<DetailResponse>> = flow {
        emit(Result.Loading())

        val result = service.getDetailMovies(movie_id)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getVideoMovie(movie_id: Int): Flow<Result<VideoResponse>> = flow {
        emit(Result.Loading())

        val result = service.getVideoMovies(movie_id)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getSearchMovie(
        query: String,
        page: Int,
        language: String
    ): Flow<Result<SearchMovieResponse>> = flow {

        emit(Result.Loading())

        val result = service.getSearchMovies(query, page, false, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getDiscoverMovie(
        with_genres: String,
        page: Int,
        include_adult: Boolean,
        language: String
    ): Flow<Result<DiscoverMovieResponse>> = flow {

        emit(Result.Loading())

        val result = service.getDiscoverMovies(with_genres, page, false, language)

        if (result.isSuccessful) {
            result.body()?.let { response ->
                emit(Result.Success(response))
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }
}