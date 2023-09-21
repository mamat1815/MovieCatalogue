package com.mbamgn.moviecatalogue.utils

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.di.Injection
import com.mbamgn.moviecatalogue.ui.detail.DetailActivityViewModel
import com.mbamgn.moviecatalogue.ui.favorite.movie.FavoriteMovieFragmentViewModel
import com.mbamgn.moviecatalogue.ui.favorite.tv_show.FavoriteTvShowFragmentViewModel
import com.mbamgn.moviecatalogue.ui.main.movie.MovieFragmentViewModel
import com.mbamgn.moviecatalogue.ui.main.tv_show.TvShowFragmentViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val dataRepository: DataRepository) :
    ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MovieFragmentViewModel::class.java) -> MovieFragmentViewModel(
                dataRepository
            ) as T
            modelClass.isAssignableFrom(TvShowFragmentViewModel::class.java) -> TvShowFragmentViewModel(
                dataRepository
            ) as T
            modelClass.isAssignableFrom(DetailActivityViewModel::class.java) -> DetailActivityViewModel(
                dataRepository
            ) as T
            modelClass.isAssignableFrom(FavoriteMovieFragmentViewModel::class.java) -> FavoriteMovieFragmentViewModel(
                dataRepository
            ) as T
            modelClass.isAssignableFrom(FavoriteTvShowFragmentViewModel::class.java) -> FavoriteTvShowFragmentViewModel(
                dataRepository
            ) as T
            else -> throw IllegalArgumentException("Unknown ViewModel: " + modelClass.name)
        }

    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: ViewModelFactory(Injection.repository(context)).apply {
                    INSTANCE = this
                }
            }
    }

}