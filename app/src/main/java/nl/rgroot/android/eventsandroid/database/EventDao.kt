package nl.rgroot.android.eventsandroid.database

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import nl.rgroot.android.eventsandroid.LiveEventList
import nl.rgroot.android.eventsandroid.models.Event

@Dao
interface EventDao {
    // Get functions
    @Query("SELECT * FROM Events")
    fun getAllEvents(): LiveEventList

    // Insert functions
    @Insert
    fun insert(event: Event)

    // Update functions

    // Delete functions
    @Query("DELETE FROM Events")
    fun deleteAll()
}