package com.example.movieapi.domain.model

data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val posterPath: String?,
    val releaseDate: String,
    val voteAverage: Double,
    val backdropPath: String? = null,
    val genres: List<com.example.movieapi.data.model.Genre> = emptyList(),
    val runtime: Int? = null,
    val status: String? = null,
    val tagline: String? = null
)