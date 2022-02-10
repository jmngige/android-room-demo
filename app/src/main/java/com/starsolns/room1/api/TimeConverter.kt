package com.starsolns.room1.api

import androidx.room.TypeConverter
import java.sql.Date

class TimeConverter {
    @TypeConverter
    fun toDate(value: Long?) : Date? {
        return if(value == null) null else Date(value)
    }

    @TypeConverter
    fun toLong(value: Date?) : Long? {
        return value?.time
    }
}