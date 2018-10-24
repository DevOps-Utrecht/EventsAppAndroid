package nl.rgroot.android.eventsandroid

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface EventSourceDao{
    @Query("SELECT * FROM EventSource")
    fun getAllEventSources(): List<EventSource>

    @Insert
    fun insert(eventSource: EventSource)
}