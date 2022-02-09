package com.starsolns.room1.views

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.starsolns.room1.R
import com.starsolns.room1.data.database.Movie

class MovieAdapter(private val context: Context, private val itemClickListener: ItemClickListener) :
    RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    private var movieList = emptyList<Movie>()

    interface ItemClickListener{
        fun OnItemClick(movie: Movie)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_list, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = movieList[position]
        holder.setValues(currentItem.title, currentItem.director, currentItem.description)
        holder.itemView.setOnClickListener {
            itemClickListener.OnItemClick(currentItem)
        }
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var title = itemView.findViewById<TextView>(R.id.movie_title)
        var director = itemView.findViewById<TextView>(R.id.movie_director)
        var description = itemView.findViewById<TextView>(R.id.movie_description)



        fun setValues(tiitle: String, diirector: String, deescription: String){
            title.text = tiitle
            director.text = diirector
            description.text = deescription
        }
    }
    fun setMovies(movie: List<Movie>){
        this.movieList = movie
        notifyDataSetChanged()
    }
}