package nl.rgroot.android.eventsandroid.utilities

import biweekly.Biweekly
import biweekly.ICalendar
import java.net.URL

class ICalHelper {
    companion object {
        fun createFromUrl(url: String): ICalendar {
            val response = URL(url).readText()
            val ical = Biweekly.parse(response).first()
            return ical
        }
    }
}