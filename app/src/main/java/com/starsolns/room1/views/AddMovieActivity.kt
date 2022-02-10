package com.starsolns.room1.views

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.starsolns.room1.R
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.databinding.ActivityAddMovieBinding
import com.starsolns.room1.utils.MainViewModel
import java.util.*

class AddMovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddMovieBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]

        binding.btnCancel.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }

        binding.btnSave.setOnClickListener {
            saveMovieDetails()

        }
    }

    private fun saveMovieDetails() {
        val tittle = binding.addTitle.text.toString()
        val director = binding.addDirector.text.toString()
        val description = binding.addDescription.text.toString()
        val currentTime = Calendar.getInstance().time

        if(TextUtils.isEmpty(tittle) || TextUtils.isEmpty(director) || TextUtils.isEmpty(description)){
            Snackbar.make(findViewById(android.R.id.content), "Please fill all the blank spaces", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(resources.getColor(android.R.color.holo_red_light))
                .setActionTextColor(resources.getColor(android.R.color.white))
                .show()
        }else{

            val movie = Movie(0, tittle, director, description)
            viewModel.addMovie(movie)
            val intent = Intent(this, MainActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(intent)
            overridePendingTransition(0,0)
            finish()
        }
    }
}