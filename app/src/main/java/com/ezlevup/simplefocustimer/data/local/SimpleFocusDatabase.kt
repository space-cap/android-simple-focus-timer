package com.ezlevup.simplefocustimer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.ezlevup.simplefocustimer.data.local.dao.FocusSessionDao
import com.ezlevup.simplefocustimer.data.local.entity.FocusSessionEntity

@Database(entities = [FocusSessionEntity::class], version = 1)
abstract class SimpleFocusDatabase : RoomDatabase() {
    abstract fun focusSessionDao(): FocusSessionDao
}
