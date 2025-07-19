package com.example.movieapi.presentation.home.Movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.movieapi.databinding.FragmentMovieBinding
import com.example.movieapi.presentation.home.ViewModels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter: MoviesAdapter

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = MoviesAdapter { movie ->
            val action = MovieFragmentDirections
                .actionMovieFragmentToMovieDetailsFragment(movie.id)
            findNavController().navigate(action)
        }

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.recyclerViewMovies.layoutManager = layoutManager
        binding.recyclerViewMovies.adapter = adapter

        // Infinite scroll
        binding.recyclerViewMovies.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val totalItemCount = layoutManager.itemCount
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()

                if (lastVisibleItemPosition + 4 >= totalItemCount) {
                    viewModel.fetchNowPlayingMovies()
                }
            }
        })

        viewModel.movie.observe(viewLifecycleOwner) { movies ->
            adapter.submitList(movies)
        }

        viewModel.isLoading.observe(viewLifecycleOwner) { isLoading ->
            binding.progressBar.isVisible = isLoading
            binding.swipeRefresh.isRefreshing = isLoading
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.fetchNowPlayingMovies(refresh = true)
        }

        viewModel.fetchNowPlayingMovies()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
