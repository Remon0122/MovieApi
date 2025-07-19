package com.example.movieapi.presentation.home.reted

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapi.databinding.FragmentTopRatedBinding
import com.example.movieapi.presentation.details.MovieDetailsFragmentArgs
import com.example.movieapi.presentation.home.ViewModels.TopRatedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class TopRatedFragment : Fragment() {
    private var _binding : FragmentTopRatedBinding? = null
    private val binding get() = _binding!!

    private lateinit var adapter : TopRatedAdapter

    val viewModel : TopRatedViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTopRatedBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TopRatedAdapter{ movie ->
            val action = TopRatedFragmentDirections
                .actionTopRatedFragmentToMovieDetailsFragment(movie.id)
            findNavController().navigate(action)
        }
        binding.recyclerViewMovies.adapter = adapter
        binding.recyclerViewMovies.layoutManager = GridLayoutManager(requireContext(),2)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.topRatedMovies.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}