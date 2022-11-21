package com.example.kreditplusandroidassessment.data.local.now_playing

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import kotlinx.coroutines.flow.Flow

@Dao
interface NowPlayingDao {
    @Query("SELECT * FROM nowplaying")
    fun getAllNowPlaying(): Flow<List<NowPlaying>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlaying(nowPlaying: List<NowPlaying>)

    @Query("DELETE FROM nowplaying")
    suspend fun deleteAllNowPlaying()
}