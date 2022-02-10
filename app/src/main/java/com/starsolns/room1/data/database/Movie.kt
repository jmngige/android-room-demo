package com.starsolns.room1.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable
import java.util.*

@Entity(tableName = "movie_table")
data class Movie(
     @PrimaryKey(autoGenerate = true)
     val id: Int,
     val title: String,
     val director: String,
     val description: String,
     //val lastUpdated: Date?
): Serializable