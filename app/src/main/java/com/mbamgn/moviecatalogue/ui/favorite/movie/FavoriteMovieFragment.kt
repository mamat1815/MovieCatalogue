package com.mbamgn.moviecatalogue.ui.favorite.movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.databinding.FragmentFavoriteMovieBinding
import com.mbamgn.moviecatalogue.ui.favorite.adapter.FavoriteMovieAdapter
import com.mbamgn.moviecatalogue.utils.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteMovieFragment : Fragment() {
    private lateinit var movieAdapter: FavoriteMovieAdapter
    private var _binding: FragmentFavoriteMovieBinding? = null
    private val binding get() = _binding
    private val viewModel by lazy {
        val viewModelFactory = activity?.application?.let {
            ViewModelFactory.getInstance(requireContext())
        }
        viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(FavoriteMovieFragmentViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout? {
        _binding = FragmentFavoriteMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {

            //Adapter
            movieAdapter = FavoriteMovieAdapter()

            lifecycleScope.launchWhenCreated {
                viewModel?.getFavMovie()?.collectLatest { data ->
                    movieAdapter.submitData(data)
                }
            }

            //Empty Handler
            viewLifecycleOwner.lifecycleScope.launch {
                movieAdapter.loadStateFlow.collectLatest {
                    if (it.refresh is LoadState.NotLoading) {
                        binding?.tvFavMovieStatus?.apply {
                            isVisible = movieAdapter.itemCount < 1
                            text = resources.getString(R.string.no_fav_movie)
                        }
                    }
                }
            }

            //RecyclerView
            rvFavMovie.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            }

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}