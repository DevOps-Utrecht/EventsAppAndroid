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
    // Database Dao
    abstract fun eventSourceDao(): EventSourceDao
    abstract fun eventDao(): EventDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        /**
         * Singleton database object
         */
        fun getDB(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "app.db"
                    )
                        // Reconstuct DB by destroying DB (if no migrations).
                        .fallbackToDestructiveMigration()
                        .addCallback(object: Callback(){
                            override fun onOpen(db: SupportSQLiteDatabase) {
                                super.onOpen(db)
                                dbOnOpenCallback(INSTANCE!!)
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

        /**
         * Callback function on database open.
         * Deletes all [Event] objects and inserts 1 new [Event].
         * TODO Delete reseting the database?
         */
        private fun dbOnOpenCallback(db: AppDatabase){
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
