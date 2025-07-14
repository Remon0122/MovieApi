package com.example.movieapi.data.repository

import com.example.movieapi.data.mapper.toDomain
import com.example.movieapi.data.mapper.toEntity
import com.example.movieapi.data.room.dao.MovieDao
import com.example.movieapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(
    private val dao: MovieDao
) : FavoritesRepository {

    override suspend fun addToFavorites(movie: Movie) {
        dao.insert(movie.toEntity())
    }

    override suspend fun removeFromFavorites(movieId: Int) {
        dao.deleteById(movieId)
    }

    override suspend fun isFavorite(movieId: Int): Boolean {
        return dao.isFavorite(movieId)
    }

    override fun getAllFavorites(): Flow<List<Movie>> {
        return dao.getAll().map { list -> list.map { it.toDomain() } }
    }
}
