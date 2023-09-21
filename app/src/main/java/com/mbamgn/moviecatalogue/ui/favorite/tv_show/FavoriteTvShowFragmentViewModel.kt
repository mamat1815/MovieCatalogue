package com.mbamgn.moviecatalogue.ui.favorite.tv_show

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.TvShowEntity
import kotlinx.coroutines.flow.Flow

class FavoriteTvShowFragmentViewModel(private val repository: DataRepository) : ViewModel() {

    fun getFavTvShow(): Flow<PagingData<TvShowEntity>> = repository.getFavTvShow()

}