package com.example.movieapi.data.repository

import com.example.movieapi.data.api.TMDBApiService
import com.example.movieapi.data.mapper.toDomain
import com.example.movieapi.data.mapper.toEntity
import com.example.movieapi.data.mappers.toDomain
import com.example.movieapi.data.room.dao.MovieDao
import com.example.movieapi.domain.model.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: TMDBApiService,
    private val movieDao: MovieDao // <-- Inject MovieDao
) : MoviesRepository {


    override fun getFavoritesMovie(): Flow<List<Movie>> {
        return movieDao.getAllFavorites().map { it.map { entity -> entity.toDomain() } }
    }


    override suspend fun addMovieToFavorites(movie: Movie) {
        movieDao.insert(movie.toEntity())
    }

    override suspend fun removeMovieFromFavorites(movie: Movie) {
        movieDao.delete(movie.id)
    }

    override suspend fun isMovieFavorite(movieId: Int): Boolean {
        return movieDao.isFavorite(movieId)
    }

    // من API
    override suspend fun getTopRatedMovies(): List<Movie> {
        return api.getTopRatedMovies().results.map { it.toDomain() }
    }

    override suspend fun getNowPlayingMovies(page: Int): List<Movie> {
        return api.getNowPlayingMovies(page = page).results.map { it.toDomain() }
    }

    override suspend fun searchMovies(query: String): List<Movie> {
        return api.searchMovies(query = query).results.map { it.toDomain() }
    }

    override suspend fun getMovieDetails(movieId: Int): Movie {
        return api.getMovieDetails(movieId = movieId).toDomain()
    }
}
