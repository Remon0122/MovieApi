import com.example.movieapi.data.repository.MoviesRepository
import javax.inject.Inject

class IsFavoriteUseCase @Inject constructor(
    private val repository: MoviesRepository
) {
    suspend operator fun invoke(movieId: Int): Boolean = repository.isMovieFavorite(movieId)
}
