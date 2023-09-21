package com.mbamgn.moviecatalogue.data.local

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagingSource
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.data.TvShowEntity
import com.mbamgn.moviecatalogue.data.local.database.FavDataDao
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class LocalDataSource private constructor(private val mFavDataDao: FavDataDao) {

    private val executorService: ExecutorService = Executors.newSingleThreadExecutor()

    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite: LiveData<Boolean> = _isFavorite

    fun insertFavMovie(data: MovieEntity) =
        executorService.execute { mFavDataDao.insertMovie(data) }

    fun deleteMovie(data: MovieEntity) = executorService.execute { mFavDataDao.deleteMovie(data) }
    fun getFavMovie(): PagingSource<Int, MovieEntity> = mFavDataDao.getFavMovie()

    fun checkFavMovie(id: Int) {
        executorService.execute {
            val count = mFavDataDao.getMovieCount(id)
            if (count != null) {
                _isFavorite.postValue(true)
            } else {
                _isFavorite.postValue(false)
            }
        }
    }

    fun insertTvShow(data: TvShowEntity) =
        executorService.execute { mFavDataDao.insertTvShow(data) }

    fun getFavTvShow(): PagingSource<Int, TvShowEntity> = mFavDataDao.getFavTvShow()
    fun deleteTvShow(data: TvShowEntity) =
        executorService.execute { mFavDataDao.deleteTvShow(data) }

    fun checkFavTvShow(id: Int) {
        executorService.execute {
            val count = mFavDataDao.getTvShowCount(id)
            if (count != null) {
                _isFavorite.postValue(true)
            } else {
                _isFavorite.postValue(false)
            }
        }
    }

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(dataDao: FavDataDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(dataDao)
    }

}