package nl.rgroot.android.eventsandroid.view_models

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import nl.rgroot.android.eventsandroid.LiveEventList
import nl.rgroot.android.eventsandroid.models.Event
import nl.rgroot.android.eventsandroid.repositories.EventRepository

class EventViewModel(application: Application) : AndroidViewModel(application) {
    val repository: EventRepository
    var allEvents: LiveEventList

    init {
        repository = EventRepository(application)
        allEvents = repository.allEvents
    }

    fun insert(event: Event) {
        repository.insert(event)
    }
}