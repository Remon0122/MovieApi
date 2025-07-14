package com.example.movieapi.data.repository

import com.example.movieapi.data.api.TMDBApiService
import com.example.movieapi.data.mapper.toDomain
import com.example.movieapi.data.mappers.toDomain
import com.example.movieapi.domain.model.Movie
import com.example.movieapi.domain.repository.MoviesRepository
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val api: TMDBApiService
) : MoviesRepository {

    override suspend fun getPopularMovies(apiKey: String): List<Movie> {
        return api.getPopularMovies(apiKey).results.map { it.toDomain() }
    }

    override suspend fun getTopRatedMovies(apiKey: String): List<Movie> {
        return api.getTopRatedMovies(apiKey).results.map { it.toDomain() }
    }

    override suspend fun getNowPlayingMovies(apiKey: String): List<Movie> {
        return api.getNowPlayingMovies(apiKey).results.map { it.toDomain() }
    }

    override suspend fun searchMovies(apiKey: String, query: String): List<Movie> {
        return api.searchMovies(apiKey, query).results.map { it.toDomain() }
    }

    override suspend fun getMovieDetails(apiKey: String, movieId: Int): Movie {
        return api.getMovieDetails(movieId, apiKey).toDomain()
    }
}
