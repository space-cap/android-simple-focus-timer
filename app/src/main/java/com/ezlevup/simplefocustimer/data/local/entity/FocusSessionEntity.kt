package com.ezlevup.simplefocustimer.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "focus_sessions")
data class FocusSessionEntity(
        @PrimaryKey(autoGenerate = true) val id: Long = 0,
        val startTime: Long,
        val durationSeconds: Long
)
