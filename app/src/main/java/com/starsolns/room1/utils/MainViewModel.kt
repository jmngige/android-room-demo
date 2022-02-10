package com.starsolns.room1.utils

import android.app.Application
import androidx.lifecycle.*
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.data.database.MovieDatabase
import com.starsolns.room1.data.repository.MovieRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository : MovieRepository
    val readMovies  : LiveData<List<Movie>>

    init {
        val movieDao = MovieDatabase.getDatabase(application).movieDao()
        repository = MovieRepository(movieDao)
        readMovies  = repository.readMovies
    }



    fun addMovie(movie: Movie){
       viewModelScope.launch {
           repository.insertMovie(movie)
       }
    }

    fun updateMovie(movie: Movie){
        viewModelScope.launch {
            repository.updateMovie(movie)
        }
    }

    fun deleteMovie(movie: Movie){
        viewModelScope.launch {
            repository.deleteMovie(movie)
        }
    }

    fun deleteAllMovies(){
        viewModelScope.launch {
            repository.deleteALlMovies()
        }
    }

}