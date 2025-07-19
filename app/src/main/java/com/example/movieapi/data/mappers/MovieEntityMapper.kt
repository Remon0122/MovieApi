package com.example.movieapi.data.mapper

import com.example.movieapi.data.room.entity.MovieEntity
import com.example.movieapi.domain.model.Movie

// من Entity → إلى Domain
fun MovieEntity.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        backdropPath = backdropPath
        // ممكن تضيف runtime و genres و status إذا كنت بتخزنهم كمان
    )
}


// من Domain → إلى Entity (للحفظ في Room)
fun Movie.toEntity(): MovieEntity {
    return MovieEntity(
        id = id,
        title = title,
        overview = overview,
        posterPath = posterPath,
        releaseDate = releaseDate,
        voteAverage = voteAverage,
        backdropPath = backdropPath
    )
}

