package com.mbamgn.moviecatalogue.ui.favorite.movie


import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.MovieEntity
import kotlinx.coroutines.flow.Flow

class FavoriteMovieFragmentViewModel(private val repository: DataRepository) : ViewModel() {

    fun getFavMovie(): Flow<PagingData<MovieEntity>> = repository.getFavMovie()
}
