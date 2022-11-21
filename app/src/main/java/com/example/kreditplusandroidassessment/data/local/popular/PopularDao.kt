package com.example.kreditplusandroidassessment.data.local.popular

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.kreditplusandroidassessment.domain.model.Popular
import kotlinx.coroutines.flow.Flow

@Dao
interface PopularDao {

    @Query("SELECT * FROM popular")
    fun getAllPopularMovie(): Flow<List<Popular>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPopularMovie(popular: List<Popular>)

    @Query("DELETE FROM popular")
    suspend fun deleteAllPopularMovie()
}