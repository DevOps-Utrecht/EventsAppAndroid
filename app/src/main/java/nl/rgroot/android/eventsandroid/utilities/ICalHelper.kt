package nl.rgroot.android.eventsandroid.utilities

import biweekly.Biweekly
import biweekly.ICalendar
import java.net.URL

/**
 * Helper class that gets / parses / ... ical data.
 */
class ICalHelper {
    companion object {
        fun createFromUrl(url: String): ICalendar {
            val response = URL(url).readText()
            return Biweekly.parse(response).first()
        }
    }
}