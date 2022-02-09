package com.starsolns.room1.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "movie_table")
data class Movie(
     @PrimaryKey(autoGenerate = true)
     val id: Int,
     val title: String,
     val director: String,
     val description: String
): Serializable