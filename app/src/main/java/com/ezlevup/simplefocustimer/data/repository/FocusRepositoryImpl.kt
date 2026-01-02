package com.ezlevup.simplefocustimer.data.repository

import com.ezlevup.simplefocustimer.data.local.dao.FocusSessionDao
import com.ezlevup.simplefocustimer.data.mapper.toDomainModel
import com.ezlevup.simplefocustimer.data.mapper.toEntity
import com.ezlevup.simplefocustimer.domain.model.FocusSession
import com.ezlevup.simplefocustimer.domain.repository.FocusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class FocusRepositoryImpl(private val dao: FocusSessionDao) : FocusRepository {
    override suspend fun saveSession(session: FocusSession) {
        dao.insertSession(session.toEntity())
    }

    override fun getAllSessions(): Flow<List<FocusSession>> {
        return dao.getAllSessions().map { entities -> entities.map { it.toDomainModel() } }
    }
}
