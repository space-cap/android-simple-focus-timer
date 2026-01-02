package com.ezlevup.simplefocustimer.data.mapper

import com.ezlevup.simplefocustimer.data.local.entity.FocusSessionEntity
import com.ezlevup.simplefocustimer.domain.model.FocusSession

fun FocusSessionEntity.toDomainModel(): FocusSession {
    return FocusSession(id = id, startTime = startTime, durationSeconds = durationSeconds)
}

fun FocusSession.toEntity(): FocusSessionEntity {
    return FocusSessionEntity(id = id, startTime = startTime, durationSeconds = durationSeconds)
}
