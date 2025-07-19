package com.example.movieapi.data.repository

import com.example.movieapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getFavoritesMovie(): Flow<List<Movie>>
    suspend fun addMovieToFavorites(movie: Movie)
    suspend fun removeMovieFromFavorites(movie: Movie)
    suspend fun isMovieFavorite(movieId: Int): Boolean
    suspend fun getTopRatedMovies(): List<Movie>
    suspend fun getNowPlayingMovies(page: Int): List<Movie>
    suspend fun searchMovies(query: String): List<Movie>
    suspend fun getMovieDetails(movieId: Int): Movie
}
