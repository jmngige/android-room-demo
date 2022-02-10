package com.starsolns.room1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.starsolns.room1.api.TimeConverter

@Database(entities = [Movie::class], version = 2, exportSchema = false)
@TypeConverters(TimeConverter::class)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieDao(): MovieDao

    companion object {
        private var roomInstance: MovieDatabase? = null

        private val MIGRATION_1_2: Migration = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE movie_table ADD COLUMN lastUpdated INTEGER DEFAULT NULL")
            }

        }

        fun getDatabase(context: Context): MovieDatabase {
            if (roomInstance == null) {
                synchronized(MovieDatabase::class.java) {
                    if (roomInstance == null) {
                        roomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_db"
                        )
                            .addMigrations(MIGRATION_1_2)
                            .build()
                    }
                }
            }
            return roomInstance!!
        }

    }
}