package com.example.movieapi.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.movieapi.R
import com.example.movieapi.databinding.FragmentMovieDatailsBinding
import com.example.movieapi.domain.model.Movie
import com.example.movieapi.presentation.ViewModels.MovieDetailsViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDatailsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDatailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // بدء تحميل تفاصيل الفيلم
        viewModel.getMovieDetails(args.movieId)

        // مراقبة تفاصيل الفيلم
        viewModel.movieDetails.observe(viewLifecycleOwner) { movie ->
            bindMovieData(movie)

            binding.btnFavorite.setOnClickListener {
                if (viewModel.isFavorite.value == true) {
                    viewModel.removeFromFavorite(movie)
                } else {
                    viewModel.addToFavorites(movie)
                }
            }
        }

        // مراقبة حالة المفضلة
        viewModel.isFavorite.observe(viewLifecycleOwner) { isFavorite ->
            updateFavoritesIcon(isFavorite)
        }
    }

    private fun bindMovieData(movie: Movie) {
        binding.apply {
            textTitle.text = movie.title
            textRatingRelease.text = "⭐ ${movie.voteAverage} | ${movie.releaseDate}"
            textGenres.text = movie.genres.joinToString { it.name }
            textOverview.text = movie.overview

            Glide.with(this@MovieDetailsFragment)
                .load("https://image.tmdb.org/t/p/w500${movie.backdropPath}")
                .into(imageBackdrop)

            Glide.with(this@MovieDetailsFragment)
                .load("https://image.tmdb.org/t/p/w500${movie.posterPath}")
                .into(imagePoster)
        }
    }

    private fun updateFavoritesIcon(isFavorite: Boolean) {
        val iconRes = if (isFavorite) {
            R.drawable.ic_heart_failed
        } else {
            R.drawable.ic_favorite_border
        }
        binding.btnFavorite.setBackgroundResource(iconRes)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
