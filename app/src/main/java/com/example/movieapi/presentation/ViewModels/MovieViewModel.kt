package com.example.movieapi.presentation.home.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieapi.domain.model.Movie
import com.example.movieapi.domain.usecase.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _movies = MutableLiveData<List<Movie>>()
    val movie: LiveData<List<Movie>> = _movies

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isLastPage = MutableLiveData<Boolean>(false)
    val isLastPage: LiveData<Boolean> = _isLastPage

    private var currentPage = 1
    private var isRequestInProgress = false

    fun fetchNowPlayingMovies(refresh: Boolean = false) {
        if (isRequestInProgress || isLastPage.value == true) return

        if (refresh) {
            currentPage = 1
            _movies.value = emptyList()
            _isLastPage.value = false
        }

        isRequestInProgress = true
        _isLoading.value = true

        viewModelScope.launch {
            try {
                val movies = getMoviesUseCase(currentPage)
                val currentList = _movies.value.orEmpty()

                if (movies.isNotEmpty()) {
                    _movies.value = currentList + movies
                    currentPage++
                } else {
                    _isLastPage.value = true
                }

            } catch (e: Exception) {
                // يمكنك طباعة الخطأ هنا للتصحيح
                e.printStackTrace()
            } finally {
                _isLoading.value = false
                isRequestInProgress = false
            }
        }
    }
}
