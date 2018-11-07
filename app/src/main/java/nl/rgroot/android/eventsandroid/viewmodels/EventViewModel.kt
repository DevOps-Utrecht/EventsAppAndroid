package nl.rgroot.android.eventsandroid.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import nl.rgroot.android.eventsandroid.LiveEventList
import nl.rgroot.android.eventsandroid.models.Event
import nl.rgroot.android.eventsandroid.repositories.EventRepository

class EventViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: EventRepository = EventRepository(application)
    var allEvents: LiveEventList            = repository.allEvents

    fun insert(event: Event) {
        repository.insert(event)
    }
}