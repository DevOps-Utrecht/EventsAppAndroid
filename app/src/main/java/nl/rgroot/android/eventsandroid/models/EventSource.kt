package nl.rgroot.android.eventsandroid.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize


@Parcelize
@Entity(tableName = "EventSources")
data class EventSource constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    // Non editable fields
    @ColumnInfo(name = "url")
    val url: String,

    // Editable fields
    @ColumnInfo(name = "descr")
    var descr: String
) : Parcelable {
    // Constructor to omit id being created
    @Ignore
    constructor(url: String, descr: String) : this(0, url, descr)
}
