package nl.rgroot.android.eventsandroid

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = [EventSource::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventSourceDao(): EventSourceDao
}
