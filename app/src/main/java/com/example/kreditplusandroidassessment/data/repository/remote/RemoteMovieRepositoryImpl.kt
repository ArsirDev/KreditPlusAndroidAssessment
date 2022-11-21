package com.example.kreditplusandroidassessment.data.repository.remote

import android.util.Log
import androidx.room.withTransaction
import com.example.kreditplusandroidassessment.data.local.MovieDatabase
import com.example.kreditplusandroidassessment.data.remote.api.ApiInterface
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

    override fun getAllViewUpcomingMovie(page: Int, language: String): Flow<Result<Upcoming>> = flow {
        emit(Result.Loading())

        val result = service.getUpcomingMovies(page, language)

        if (result.isSuccessful) {
            result.body()?.toUpcomingList()?.let { response ->
                response.map { upcoming ->
                    emit(Result.Success(upcoming))
                }
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }

    override fun getAllViewPopularMovie(page: Int, language: String): Flow<Result<Popular>> = flow {
        emit(Result.Loading())

        val result = service.getPopularMovies(page, language)

        if (result.isSuccessful) {
            result.body()?.toPopularList()?.let { response ->
                response.map { popular ->
                    emit(Result.Success(popular))
                }
            }
        } else {
            emit(Result.Error(message = "${result.code()} ${result.message()}"))
        }
    }
}