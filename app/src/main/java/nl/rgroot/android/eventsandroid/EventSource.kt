package nl.rgroot.android.eventsandroid

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull


@Entity(tableName = "EventSource")
data class EventSource constructor(
    @PrimaryKey(autoGenerate = true)
    val id: Long,

    // Non editable
    @ColumnInfo(name = "url")
    val url: String,

    // Editable
    @ColumnInfo(name = "descr")
    var descr: String
){
    // Constructor to omit id being created
    @Ignore
    constructor(url: String, descr: String) : this(0, url, descr)
}
