package com.mbamgn.moviecatalogue.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mbamgn.moviecatalogue.data.local.LocalDataSource
import com.mbamgn.moviecatalogue.data.remote.RemoteDataSource
import kotlinx.coroutines.flow.Flow

class DataRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
) : DataSource {

    val onLoading = MutableLiveData<Boolean>()
    val favorite = localDataSource.isFavorite
    val listTvShow = MutableLiveData<List<DataItem>>()
    val listMovie = MutableLiveData<List<DataItem>>()

    private val config = PagingConfig(
        enablePlaceholders = false,
        initialLoadSize = 10,
        pageSize = 10
    )

    override fun getMovie(): LiveData<List<DataItem>> {
        onLoading.value = true
        remoteDataSource.getMovieList(object : RemoteDataSource.ListMovieCallback {
            override fun onResponse(response: List<DataItem>) {
                onLoading.value = false
                listMovie.value = response
            }
        })

        return listMovie
    }


    override fun getTvShow(): LiveData<List<DataItem>> {
        onLoading.value = true
        remoteDataSource.getTvShowList(object : RemoteDataSource.ListTvShowCallback {
            override fun onResponse(response: List<DataItem>) {
                onLoading.value = false
                listTvShow.value = response
            }

        })
        return listTvShow
    }

    override fun searchMovie(query: String): LiveData<List<DataItem>> {
        onLoading.value = true
        remoteDataSource.searchMovie(query, object : RemoteDataSource.SearchMovieCallback {
            override fun onResponse(response: List<DataItem>) {
                if (response.isNullOrEmpty()) {
                    onLoading.value = false
                } else {
                    onLoading.value = false
                    listMovie.value = response
                }
            }
        })
        return listMovie
    }

    override fun searchTvShow(query: String): LiveData<List<DataItem>> {
        onLoading.value = true
        remoteDataSource.searchTvShow(query, object : RemoteDataSource.SearchTvShowCallback {
            override fun onResponse(response: List<DataItem>) {
                if (response.isNullOrEmpty()) {
                    onLoading.value = false
                } else {
                    onLoading.value = false
                    listTvShow.value = response
                }
            }
        })
        return listTvShow
    }

    override fun getDetailData(id: Int, type: String): LiveData<DataItem> {
        val detailData = MutableLiveData<DataItem>()
        val types = "movie"
        if (type == types) {
            onLoading.value = true
            remoteDataSource.getDetailMovie(id, object : RemoteDataSource.MovieDetailCallback {
                override fun onResponse(response: DataItem) {
                    onLoading.value = false
                    detailData.value = response
                }
            })
        } else {
            onLoading.value = true
            remoteDataSource.getDetailTv(id, object : RemoteDataSource.TvShowDetailCallback {
                override fun onResponse(response: DataItem) {
                    onLoading.value = false
                    detailData.value = response
                }
            })
        }
        return detailData
    }


    override fun setFavMovie(data: MovieEntity) = localDataSource.insertFavMovie(data)
    override fun deleteFavMovie(data: MovieEntity) = localDataSource.deleteMovie(data)
    override fun counterFavMovie(id: Int) = localDataSource.checkFavMovie(id)
    override fun getFavMovie(): Flow<PagingData<MovieEntity>> =
        Pager(config, pagingSourceFactory = { localDataSource.getFavMovie() }).flow

    override fun setFavTvShow(data: TvShowEntity) = localDataSource.insertTvShow(data)
    override fun deleteTvShow(data: TvShowEntity) = localDataSource.deleteTvShow(data)
    override fun counterFavTvShow(id: Int) = localDataSource.checkFavTvShow(id)
    override fun getFavTvShow(): Flow<PagingData<TvShowEntity>> =
        Pager(config, pagingSourceFactory = { localDataSource.getFavTvShow() }).flow


    companion object {
        @Volatile
        private var INSTANCE: DataRepository? = null

        fun getInstance(
            remoteRepository: RemoteDataSource,
            localDataSource: LocalDataSource,
        ): DataRepository? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = DataRepository(remoteRepository, localDataSource)
                }
            }
            return INSTANCE
        }
    }

}