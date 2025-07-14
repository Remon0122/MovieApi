package com.example.movieapi.domain.usecase

import com.example.movieapi.domain.repository.MoviesRepository

class SearchMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(apiKey: String, query: String) = repository.searchMovies(apiKey, query)
}
