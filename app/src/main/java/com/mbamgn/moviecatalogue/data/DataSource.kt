package com.mbamgn.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import kotlinx.coroutines.flow.Flow

interface DataSource {

    fun getMovie(): LiveData<List<DataItem>>
    fun getTvShow(): LiveData<List<DataItem>>
    fun getDetailData(id: Int, type: String): LiveData<DataItem>

    fun searchMovie(query: String): LiveData<List<DataItem>>
    fun searchTvShow(query: String): LiveData<List<DataItem>>

    fun setFavMovie(data: MovieEntity)
    fun deleteFavMovie(data: MovieEntity)
    fun counterFavMovie(id: Int)
    fun getFavMovie(): Flow<PagingData<MovieEntity>>

    fun setFavTvShow(data: TvShowEntity)
    fun deleteTvShow(data: TvShowEntity)
    fun counterFavTvShow(id: Int)
    fun getFavTvShow(): Flow<PagingData<TvShowEntity>>

}