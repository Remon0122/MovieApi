package com.example.movieapi.presentation.home.Movie

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapi.databinding.ItemMovieBinding
import com.example.movieapi.domain.model.Movie

class MoviesAdapter (private val onMovieCLick :(Movie)-> Unit):
    ListAdapter<Movie, MoviesAdapter.MovieViewHolder>(MovieDiffCallback()){

    inner class MovieViewHolder(private val binding : ItemMovieBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: Movie){
            binding.movieTitle.text = movie.title
            binding.movieRating.text = movie.voteAverage.toString()

            val imageUrl = "https://image.tmdb.org/t/p/w500${movie.posterPath}"
            Glide.with(binding.movieImage.context)
                .load(imageUrl)
                .into(binding.movieImage)

            binding.root.setOnClickListener {
                onMovieCLick(movie)
            }
        }
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MovieViewHolder {
        val viewBinding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(viewBinding)
    }

    override fun onBindViewHolder(
        holder: MovieViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean =
            oldItem == newItem
        }
    }