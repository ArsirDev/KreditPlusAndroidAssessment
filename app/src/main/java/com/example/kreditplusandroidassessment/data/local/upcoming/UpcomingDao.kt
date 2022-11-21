package com.example.kreditplusandroidassessment.data.local.upcoming

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import kotlinx.coroutines.flow.Flow

@Dao
interface UpcomingDao {

    @Query("SELECT * FROM upcoming")
    fun getUpcomingMovie(): Flow<List<Upcoming>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovie(upcoming: List<Upcoming>)

    @Query("DELETE FROM upcoming")
    suspend fun deleteAllUpcomingMovie()
}