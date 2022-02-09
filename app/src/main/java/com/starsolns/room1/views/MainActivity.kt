package com.starsolns.room1.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.room1.R
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.databinding.ActivityMainBinding
import com.starsolns.room1.utils.MainViewModel
import java.util.*

const val MOVIE_DETAILS = "movie_details"
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private lateinit var movieAdapter: MovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.fab.setOnClickListener {
            val intent = Intent(this, AddMovieActivity::class.java)
            startActivity(intent)
        }

        movieAdapter = MovieAdapter(this, object: MovieAdapter.ItemClickListener{
            override fun OnItemClick(movie: Movie) {
                val intent = Intent(applicationContext, EditMovieActivity::class.java)
                intent.putExtra(MOVIE_DETAILS, movie)
                startActivity(intent)
            }

        })
        movieAdapter.notifyDataSetChanged()
        binding.moviewRv.layoutManager = LinearLayoutManager(this)
        binding.moviewRv.adapter = movieAdapter

        viewModel.readMovies.observe(this, Observer{ movies->
            movieAdapter.setMovies(movies)
        })


    }
}

