package nl.rgroot.android.eventsandroid.models

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import android.support.annotation.NonNull
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "Events")
data class Event constructor(
    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    val id: Long,

    // Non editable variables
    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "descr")
    val descr: String,

    @ColumnInfo(name = "date")
    val date: Date

    // Editable variables
) : Parcelable {
    // Constructor to omit id being created
    @Ignore
    constructor(title: String, descr: String, date: Date) : this(0, title, descr, date)
}
