package com.example.movieapi.domain.usecase

import com.example.movieapi.data.repository.MoviesRepository
import com.example.movieapi.domain.model.Movie
import javax.inject.Inject


class SearchMoviesUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(query: String): List<Movie> {
        return repository.searchMovies(query)
    }
}
