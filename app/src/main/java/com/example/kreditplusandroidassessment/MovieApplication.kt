package com.example.kreditplusandroidassessment

import android.app.Application
import com.example.kreditplusandroidassessment.util.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MovieApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MovieApplication)
            modules(
                local,
                remote,
                splash,
                slider,
                home
            )
        }
    }
}