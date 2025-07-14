package com.example.movieapi.domain.usecase

import com.example.movieapi.domain.repository.MoviesRepository

class GetPopularMoviesUseCase(private val repository: MoviesRepository) {
    suspend operator fun invoke(apiKey: String) = repository.getPopularMovies(apiKey)
}