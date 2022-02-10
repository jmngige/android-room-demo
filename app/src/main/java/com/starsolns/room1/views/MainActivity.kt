package com.starsolns.room1.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.starsolns.room1.R
import com.starsolns.room1.data.database.Movie
import com.starsolns.room1.databinding.ActivityMainBinding
import com.starsolns.room1.utils.MainViewModel
import java.util.*

const val MOVIE_DETAILS = "movie_details"
const val MOVIE_ID = "movie_id"
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
                intent.putExtra(MOVIE_ID, movie.id)
                intent.putExtra(MOVIE_DETAILS, movie)
                startActivity(intent)
            }

        }, object: MovieAdapter.ItemDeleteListener{
            override fun OnItemDelete(movie: Movie) {

                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Delete ${movie.title}")
                    .setMessage("Are you sure you want to delete ${movie.title}?")
                    .setNegativeButton("No"){_,_->

                    }
                    .setPositiveButton("Yes"){_,_->
                        viewModel.deleteMovie(movie)
                        Toast.makeText(this@MainActivity, "Movie deleted successfully", Toast.LENGTH_LONG).show()
                    }
                val dialog: AlertDialog = builder.create()
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }

        })

        movieAdapter.notifyDataSetChanged()
        binding.moviewRv.layoutManager = LinearLayoutManager(this)
        binding.moviewRv.adapter = movieAdapter

        if(movieAdapter.itemCount == 0){
            binding
        }

        viewModel.readMovies.observe(this, Observer{ movies->
            movieAdapter.setMovies(movies)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.delete_menu, menu)
        if (movieAdapter.itemCount  == 0){
            return false
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.delete_all -> {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Delete all")
                builder.setMessage("Are you sure you want to delete all movies?")
                builder.setNegativeButton("No"){_,_->}
                builder.setPositiveButton("Yes"){_,_->
                    viewModel.deleteAllMovies()
                    Toast.makeText(this@MainActivity, "All movies deleted successfully", Toast.LENGTH_LONG).show()
                }

                val dialog: AlertDialog = builder.create()
                dialog.setCanceledOnTouchOutside(false)
                dialog.show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}

