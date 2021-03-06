package com.starsolns.room1.data.repository

import androidx.lifecycle.LiveData
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.data.database.MovieDao

class MovieRepository(private val movieDao: MovieDao) {

   val readMovies : LiveData<List<Movie>> = movieDao.readMovies()

   suspend fun insertMovie(movie: Movie){
       movieDao.insert(movie)
   }

    suspend fun updateMovie(movie: Movie){
        movieDao.update(movie)
    }

    suspend fun deleteMovie(movie: Movie){
        movieDao.delete(movie)
    }

    suspend fun deleteALlMovies(){
        movieDao.deleteAllMovies()
    }
}