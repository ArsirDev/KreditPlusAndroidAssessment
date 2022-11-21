package com.example.kreditplusandroidassessment.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kreditplusandroidassessment.data.local.MovieDatabase.Companion.dbVersion
import com.example.kreditplusandroidassessment.data.local.now_playing.NowPlayingDao
import com.example.kreditplusandroidassessment.data.local.popular.PopularDao
import com.example.kreditplusandroidassessment.data.local.upcoming.UpcomingDao
import com.example.kreditplusandroidassessment.domain.model.NowPlaying
import com.example.kreditplusandroidassessment.domain.model.Popular
import com.example.kreditplusandroidassessment.domain.model.Upcoming
import com.example.kreditplusandroidassessment.util.Converter
import com.google.gson.Gson

@Database(entities = [NowPlaying::class, Upcoming::class, Popular::class], version = dbVersion)
@TypeConverters(Converter::class)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun nowPlayingDao(): NowPlayingDao

    abstract fun upcomingDao(): UpcomingDao

    abstract fun popularDao(): PopularDao

    companion object {
        @Volatile
        private var instance: MovieDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: createDatabase(context).also { dbInstance ->
                this.instance = dbInstance
            }
        }

        private fun createDatabase(context: Context) = Room
            .databaseBuilder(context.applicationContext, MovieDatabase::class.java, dbName)
            .addTypeConverter(Converter(Gson()))
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

        const val dbVersion = 1
        const val dbName = "MovieDB"
    }
}