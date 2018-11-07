package nl.rgroot.android.eventsandroid.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import nl.rgroot.android.eventsandroid.models.Event
import nl.rgroot.android.eventsandroid.models.EventSource
import org.jetbrains.anko.doAsync
import java.util.*

@Database(entities = [
    EventSource::class,
    Event::class
], version = 1)
@TypeConverters(DbConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun eventSourceDao(): EventSourceDao
    abstract fun eventDao(): EventDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDB(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app.db"
                    )
                        .fallbackToDestructiveMigration()
                        .addCallback(object: Callback(){
                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                dbCallback(INSTANCE!!)
                            }
                        })
                        .build()
                }
            }
            return INSTANCE
        }

        fun destroyInstance() {
            INSTANCE = null
        }

        private fun dbCallback(db: AppDatabase){
            doAsync {
                db.eventDao().run {
                    deleteAll()
                    insert(
                        Event(
                        "TestEvent",
                        "Dit is een test event.",
                        Date()
                    ))
                }
            }
        }
    }
}
