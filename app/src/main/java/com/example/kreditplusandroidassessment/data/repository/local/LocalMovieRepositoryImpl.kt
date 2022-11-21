package com.example.kreditplusandroidassessment.data.repository.local

import com.example.kreditplusandroidassessment.domain.repository.local.LocalMovieRepository
import com.example.kreditplusandroidassessment.util.SessionManager
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class LocalMovieRepositoryImpl(
    private val sessionManager: SessionManager
): LocalMovieRepository {
    override suspend fun setFirstInstallStatus(status: Boolean) {
        return sessionManager.setFirstInstall(status)
    }

    override suspend fun getFirstInstallStatus(): Flow<Boolean> {
        return sessionManager.getFirstInstall
    }
}