package com.starsolns.room1.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.starsolns.room1.R
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.databinding.ActivityEditMovieBinding

class EditMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMovieBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra(MOVIE_DETAILS) as Movie
        binding.editTitle.setText(movie.title)
        binding.editDirector.setText(movie.director)
        binding.editDescription.setText(movie.description)


    }
}