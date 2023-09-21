package com.mbamgn.moviecatalogue.ui.favorite.tv_show

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
import com.mbamgn.moviecatalogue.databinding.FragmentFavoriteTvShowBinding
import com.mbamgn.moviecatalogue.ui.favorite.adapter.FavoriteTvShowAdapter
import com.mbamgn.moviecatalogue.utils.ViewModelFactory
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class FavoriteTvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: FavoriteTvShowAdapter
    private var _binding: FragmentFavoriteTvShowBinding? = null
    private val binding get() = _binding
    private val viewModel by lazy {
        val viewModelFactory = activity?.application?.let {
            ViewModelFactory.getInstance(requireContext())
        }
        viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(FavoriteTvShowFragmentViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout? {
        _binding = FragmentFavoriteTvShowBinding.inflate(layoutInflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.apply {
            //Adapter
            tvShowAdapter = FavoriteTvShowAdapter()
            lifecycleScope.launchWhenCreated {
                viewModel?.getFavTvShow()?.collectLatest {
                    tvShowAdapter.submitData(it)
                }
            }

            //Empty Handler
            viewLifecycleOwner.lifecycleScope.launch {
                tvShowAdapter.loadStateFlow.collectLatest {
                    if (it.refresh is LoadState.NotLoading) {
                        binding?.tvFavTvShowStatus?.apply {
                            isVisible = tvShowAdapter.itemCount < 1
                            text = resources.getString(R.string.no_fav_tv_show)
                        }
                    }
                }
            }

            //RecyclerView
            rvFavTvShow.apply {
                adapter = tvShowAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}