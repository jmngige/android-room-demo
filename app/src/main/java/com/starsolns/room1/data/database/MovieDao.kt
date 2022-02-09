package com.starsolns.room1.data.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MovieDao {

    @Insert
    suspend fun insert(movie: Movie)

    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun readMovies() : LiveData<List<Movie>>

    @Update
    suspend fun update(movie: Movie)

    @Delete
    suspend fun delete(movie: Movie)
}