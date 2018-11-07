package nl.rgroot.android.eventsandroid.database

import android.arch.persistence.room.TypeConverter
import java.util.*


/**
 * Converts types when communicating with the database automagically.
 */
class DbConverter {
    @TypeConverter
    fun fromTimestampToLong(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun fromDateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}