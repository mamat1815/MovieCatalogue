package com.mbamgn.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.data.TvShowEntity

class DetailActivityViewModel(private val repository: DataRepository) : ViewModel() {

    fun getDetailData(type: String, id: Int) = repository.getDetailData(id, type)
    fun getLoad(): LiveData<Boolean> = repository.onLoading

    fun setFavMovie(data: MovieEntity) = repository.setFavMovie(data)
    fun deleteFavMovie(data: MovieEntity) = repository.deleteFavMovie(data)
    fun counterFavMovie(id: Int) = repository.counterFavMovie(id)

    fun setFavTvShow(data: TvShowEntity) = repository.setFavTvShow(data)
    fun deleteFavTvShow(data: TvShowEntity) = repository.deleteTvShow(data)
    fun counterFavTvShow(id: Int) = repository.counterFavTvShow(id)

    fun isFav(): LiveData<Boolean> = repository.favorite

}