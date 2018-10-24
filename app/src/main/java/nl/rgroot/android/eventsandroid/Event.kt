package nl.rgroot.android.eventsandroid

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Event(
    val title: String,
    val descr: String
) : Parcelable {

}
