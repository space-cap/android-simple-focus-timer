package com.ezlevup.simplefocustimer.domain.usecase

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class StartTimerUseCase {
    operator fun invoke(durationSeconds: Long): Flow<Long> = flow {
        for (i in durationSeconds downTo 0) {
            emit(i)
            if (i > 0) delay(1000)
        }
    }
}
