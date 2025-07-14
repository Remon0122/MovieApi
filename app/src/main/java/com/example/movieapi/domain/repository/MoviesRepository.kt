package com.example.movieapi.domain.repository

import com.example.movieapi.domain.model.Movie

interface MoviesRepository {
    suspend fun getPopularMovies(apiKey: String): List<Movie>
    suspend fun getTopRatedMovies(apiKey: String): List<Movie>
    suspend fun getNowPlayingMovies(apiKey: String): List<Movie>
    suspend fun searchMovies(apiKey: String, query: String): List<Movie>
    suspend fun getMovieDetails(apiKey: String, movieId: Int): Movie
}