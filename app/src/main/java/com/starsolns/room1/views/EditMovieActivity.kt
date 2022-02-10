package com.starsolns.room1.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.starsolns.room1.R
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.databinding.ActivityEditMovieBinding
import com.starsolns.room1.utils.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class EditMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditMovieBinding
    private lateinit var viewmodel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val movie = intent.getSerializableExtra(MOVIE_DETAILS) as Movie
        binding.editTitle.setText(movie.title)
        binding.editDirector.setText(movie.director)
        binding.editDescription.setText(movie.description)
        //binding.editLastUpdate.text = getFormatedDate(movie.lastUpdated)

        viewmodel = ViewModelProvider(this)[MainViewModel::class.java]

       binding.btnUpdate.setOnClickListener {
           updateMovieDetails(movie)
       }

       binding.btnCancelUpdate.setOnClickListener {
           val intent = Intent(this, MainActivity::class.java)
           intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
           finish()
       }

    }

    private fun getFormatedDate(lastUpdated: Date?): CharSequence? {
        var time = "Last Updated at "
        time+=lastUpdated?.let {
            val sdf = SimpleDateFormat("HH:mm d MMM yyy", Locale.getDefault())
            sdf.format(lastUpdated)
        } ?: "Not found"
        return time
    }

    private fun updateMovieDetails(movie: Movie) {
        val tiitle = binding.editTitle.text.toString()
        val diirector = binding.editDirector.text.toString()
        val deescription = binding.editDescription.text.toString()
        val currentTime = Calendar.getInstance().time

        val movie = Movie(movie.id, tiitle, diirector, deescription)
        viewmodel.updateMovie(movie)
        val intent = Intent(this, MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        finish()
    }
}