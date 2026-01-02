package com.ezlevup.simplefocustimer.domain.usecase

import com.ezlevup.simplefocustimer.domain.model.FocusSession
import com.ezlevup.simplefocustimer.domain.repository.FocusRepository

class SaveFocusSessionUseCase(private val repository: FocusRepository) {
    suspend operator fun invoke(session: FocusSession) {
        repository.saveSession(session)
    }
}
