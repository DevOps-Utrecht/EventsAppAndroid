package nl.rgroot.android.eventsandroid.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import nl.rgroot.android.eventsandroid.models.EventSource

@Dao
interface EventSourceDao{
    @Query("SELECT * FROM EventSources")
    fun getAllEventSources(): List<EventSource>

    @Insert
    fun insert(eventSource: EventSource)
}