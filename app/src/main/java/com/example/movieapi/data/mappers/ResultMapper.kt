package com.example.movieapi.data.mappers

import com.example.movieapi.data.model.Result
import com.example.movieapi.domain.model.Movie
fun Result.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage
    )
}
