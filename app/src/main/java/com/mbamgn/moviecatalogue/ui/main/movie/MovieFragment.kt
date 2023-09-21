package com.mbamgn.moviecatalogue.ui.main.movie

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.databinding.FragmentMovieBinding
import com.mbamgn.moviecatalogue.ui.main.adapter.MovieAdapter
import com.mbamgn.moviecatalogue.utils.ViewModelFactory

class MovieFragment : Fragment() {

    private lateinit var movieAdapter: MovieAdapter
    private lateinit var queryMovie: String
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding

    private val viewModel by lazy {
        val viewModelFactory = activity?.application?.let {
            ViewModelFactory.getInstance(requireContext())
        }
        viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(MovieFragmentViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout? {
        _binding = FragmentMovieBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        movieAdapter = MovieAdapter()

        binding?.apply {

            //Search View
            searchMode.inflateMenu(R.menu.item_menu)
            val searchView = searchMode.menu.findItem(R.id.search_data).actionView as SearchView

            searchView.apply {

                queryHint = resources.getString(R.string.search_hint)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        queryMovie = query.toString()
                        clearFocus()
                        viewModel?.searchMovie(queryMovie)?.observe(viewLifecycleOwner) {
                            movieAdapter.setMovieData(it)
                        }
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        return false
                    }
                })

            }

            //Recycler View
            rvMovie.apply {
                adapter = movieAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

            }
            viewModel?.apply {

                getListMovie().observe(viewLifecycleOwner) {
                    movieAdapter.setMovieData(it)
                }

                getLoad().observe(viewLifecycleOwner) {
                    setLoad(it)
                }
            }

        }

    }

    private fun setLoad(state: Boolean) {
        binding?.pbListMovie?.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.item_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}