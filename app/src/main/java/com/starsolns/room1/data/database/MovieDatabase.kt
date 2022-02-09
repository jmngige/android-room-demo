package com.starsolns.room1.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase: RoomDatabase() {

    abstract fun movieDao() : MovieDao

    companion object {
       private var roomInstance: MovieDatabase? = null

        fun getDatabase(context: Context): MovieDatabase {
            if(roomInstance == null){
                synchronized(MovieDatabase::class.java){
                    if (roomInstance == null){
                        roomInstance = Room.databaseBuilder(
                            context.applicationContext,
                            MovieDatabase::class.java,
                            "movie_db"
                        ).build()
                    }
                }
            }
            return roomInstance!!
        }

    }
}