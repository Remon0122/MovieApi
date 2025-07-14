package com.example.movieapi.data.mapper


import com.example.movieapi.data.model.Genre
import com.example.movieapi.data.model.MovieDetailsResponse
import com.example.movieapi.domain.model.Movie
import com.example.movieapi.data.model.Genre as GenreDto

fun GenreDto.toDomain(): Genre {
    return Genre(
        id = id,
        name = name
    )
}

fun MovieDetailsResponse.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        backdropPath = backdropPath,
        runtime = runtime,
        tagline = tagline,
        status = status,
        genres = genres.map { it.toDomain() }
    )
}
