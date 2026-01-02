package com.ezlevup.simplefocustimer.domain.usecase

import com.ezlevup.simplefocustimer.domain.model.FocusSession
import com.ezlevup.simplefocustimer.domain.repository.FocusRepository
import kotlinx.coroutines.flow.Flow

class GetFocusHistoryUseCase(private val repository: FocusRepository) {
    operator fun invoke(): Flow<List<FocusSession>> {
        return repository.getAllSessions()
    }
}
