package com.example.movieapi.presentation.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.repository.MoviesRepository
import com.example.movieapi.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val repository: MoviesRepository
) : ViewModel() {

    private val _movieDetails = MutableLiveData<Movie>()
    val movieDetails: LiveData<Movie> get() = _movieDetails

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> get() = _isFavorite

    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            val movie = repository.getMovieDetails(movieId)
            _movieDetails.value = movie
            checkIfFavorite(movie.id)
        }
    }

    fun addToFavorites(movie: Movie) {
        viewModelScope.launch {
            repository.addMovieToFavorites(movie)
            _isFavorite.value = true
        }
    }

    fun removeFromFavorite(movie: Movie) {
        viewModelScope.launch {
            repository.removeMovieFromFavorites(movie)
            _isFavorite.value = false
        }
    }

    private fun checkIfFavorite(movieId: Int) {
        viewModelScope.launch {
            val favorite = repository.isMovieFavorite(movieId)
            _isFavorite.value = favorite
        }
    }
}

