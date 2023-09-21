package com.mbamgn.moviecatalogue.ui.main.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.DataRepository

class MovieFragmentViewModel(private val repository: DataRepository) : ViewModel() {

    fun getListMovie(): LiveData<List<DataItem>> = repository.getMovie()

    fun searchMovie(query: String): LiveData<List<DataItem>> = repository.searchMovie(query)

    fun getLoad(): LiveData<Boolean> = repository.onLoading

}