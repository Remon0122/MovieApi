package com.example.movieapi.data.repository

import com.example.movieapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow


interface FavoritesRepository {
    suspend fun addToFavorites(movie: Movie)
    suspend fun removeFromFavorites(movieId: Int)
    fun getAllFavorites(): Flow<List<Movie>>
    suspend fun isFavorite(movieId: Int): Boolean
}