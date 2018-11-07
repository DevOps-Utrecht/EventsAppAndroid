package nl.rgroot.android.eventsandroid.repositories

import android.app.Application
import android.arch.lifecycle.LiveData
import nl.rgroot.android.eventsandroid.LiveEventList
import nl.rgroot.android.eventsandroid.models.Event
import nl.rgroot.android.eventsandroid.database.EventDao
import nl.rgroot.android.eventsandroid.database.AppDatabase
import org.jetbrains.anko.doAsync

class EventRepository(application: Application) {
    val eventDao : EventDao
    var allEvents: LiveEventList

    init {
        val db = AppDatabase.getDB(application)
        eventDao  = db!!.eventDao()
        allEvents = eventDao.getAllEvents()
    }

    fun insert(event: Event){
        doAsync {
            eventDao.insert(event)
        }
    }
}