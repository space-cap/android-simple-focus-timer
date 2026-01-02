package com.ezlevup.simplefocustimer.domain.repository

import com.ezlevup.simplefocustimer.domain.model.FocusSession
import kotlinx.coroutines.flow.Flow

interface FocusRepository {
    suspend fun saveSession(session: FocusSession)
    fun getAllSessions(): Flow<List<FocusSession>>
}
