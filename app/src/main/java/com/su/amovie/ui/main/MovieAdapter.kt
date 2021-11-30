package com.su.amovie.ui.main

import android.text.method.TextKeyListener.clear
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.su.amovie.R
import java.util.Collections.addAll

class MovieAdapter(val movies: ArrayList<MovieUiModel>) :
    RecyclerView.Adapter<MovieAdapter.FlowerViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class FlowerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val moveNameTxt: TextView = itemView.findViewById(R.id.txt_movie_name)
        private val movieImg: ImageView = itemView.findViewById(R.id.img_movie)
        private val movieReleaseDateTxt: TextView = itemView.findViewById(R.id.txt_movie_release_date)
        fun bind(movie: MovieUiModel) {
            movieImg.load("https://image.tmdb.org/t/p/w500${movie.url}")
            moveNameTxt.text = movie.name
            movieReleaseDateTxt.text = movie.releaseDate
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlowerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.movie_item, parent, false)

        return FlowerViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return movies.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: FlowerViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    fun addMovies(users: List<MovieUiModel>) {
        this.movies.apply {
            clear()
            addAll(users)
        }

    }
}