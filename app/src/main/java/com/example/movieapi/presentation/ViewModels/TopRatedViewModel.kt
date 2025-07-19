package com.example.movieapi.presentation.home.ViewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.data.repository.MoviesRepository
import com.example.movieapi.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TopRatedViewModel  @Inject constructor(
    val repository: MoviesRepository
) : ViewModel(){
    private val _topRatedMovies = MutableStateFlow<List<Movie>>(emptyList())
    val topRatedMovies: StateFlow<List<Movie>> = _topRatedMovies

    init {
        fetchTopRatedMovies()
    }
    private fun fetchTopRatedMovies(){
        viewModelScope.launch {
            _topRatedMovies.value = repository.getTopRatedMovies()
        }
    }
}