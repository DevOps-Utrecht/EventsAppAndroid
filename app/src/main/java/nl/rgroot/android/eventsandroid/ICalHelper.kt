package nl.rgroot.android.eventsandroid

import biweekly.Biweekly
import biweekly.ICalendar
import java.net.URL

class ICalHelper {
    fun createFromUrl(url: String): ICalendar {
        val response = URL("yourUrl").readText()
        val ical = Biweekly.parse(response).first()
        return ical
    }
}