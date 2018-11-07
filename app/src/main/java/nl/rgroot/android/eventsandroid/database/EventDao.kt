package nl.rgroot.android.eventsandroid.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import nl.rgroot.android.eventsandroid.LiveEventList
import nl.rgroot.android.eventsandroid.models.Event

@Dao
interface EventDao {
    // Get
    @Query("SELECT * FROM Events")
    fun getAllEvents(): LiveEventList

    // Insert
    @Insert
    fun insert(event: Event)

    // Update

    // Delete
    @Query("DELETE FROM Events")
    fun deleteAll()
}