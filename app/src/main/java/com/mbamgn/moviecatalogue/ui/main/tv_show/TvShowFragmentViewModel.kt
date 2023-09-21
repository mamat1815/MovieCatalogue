package com.mbamgn.moviecatalogue.ui.main.tv_show

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.DataRepository

class TvShowFragmentViewModel(private val repository: DataRepository) : ViewModel() {

    fun getListTvShow(): LiveData<List<DataItem>> = repository.getTvShow()

    fun searchTvShow(query: String): LiveData<List<DataItem>> = repository.searchTvShow(query)

    fun getLoad(): LiveData<Boolean> = repository.onLoading

}