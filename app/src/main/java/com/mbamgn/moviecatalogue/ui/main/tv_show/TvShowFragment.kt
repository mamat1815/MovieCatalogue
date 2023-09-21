package com.mbamgn.moviecatalogue.ui.main.tv_show

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.databinding.FragmentTvShowBinding
import com.mbamgn.moviecatalogue.ui.main.adapter.TvShowAdapter
import com.mbamgn.moviecatalogue.utils.ViewModelFactory

class TvShowFragment : Fragment() {

    private lateinit var tvShowAdapter: TvShowAdapter
    private lateinit var queryTvShow: String
    private var _binding: FragmentTvShowBinding? = null
    private val binding get() = _binding
    private val viewModel by lazy {
        val viewModelFactory = activity?.application?.let {
            ViewModelFactory.getInstance(requireContext())
        }
        viewModelFactory?.let {
            ViewModelProvider(
                this,
                it
            ).get(TvShowFragmentViewModel::class.java)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): ConstraintLayout? {
        _binding = FragmentTvShowBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tvShowAdapter = TvShowAdapter()

        binding?.apply {

            //Search View
            searchTvShow.inflateMenu(R.menu.item_menu)
            val searchView = searchTvShow.menu.findItem(R.id.search_data).actionView as SearchView

            searchView.apply {

                queryHint = resources.getString(R.string.search_hint)
                setOnQueryTextListener(object : SearchView.OnQueryTextListener {

                    override fun onQueryTextSubmit(query: String?): Boolean {
                        queryTvShow = query.toString()
                        clearFocus()
                        viewModel?.searchTvShow(queryTvShow)?.observe(viewLifecycleOwner) {
                            tvShowAdapter.setTvShowData(it)
                        }
                        return true
                    }

                    override fun onQueryTextChange(p0: String?): Boolean {
                        return false
                    }
                })

            }

            rvTvShow.apply {
                adapter = tvShowAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            }
        }


        viewModel?.apply {
            getListTvShow().observe(viewLifecycleOwner) {
                tvShowAdapter.setTvShowData(it)
            }

            getLoad().observe(viewLifecycleOwner) {
                setLoad(it)
            }
        }

    }

    private fun setLoad(state: Boolean) {
        binding?.pbListTvShow?.visibility = if (state) View.VISIBLE else View.INVISIBLE
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