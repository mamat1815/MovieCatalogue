package com.mbamgn.moviecatalogue.data.remote

import android.util.Log
import com.mbamgn.moviecatalogue.BuildConfig
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.ItemResponse
import com.mbamgn.moviecatalogue.data.remote.retrofit.Api
import com.mbamgn.moviecatalogue.data.remote.retrofit.Client
import com.mbamgn.moviecatalogue.utils.EspressoResource.decrement
import com.mbamgn.moviecatalogue.utils.EspressoResource.increment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDataSource(private val retrofit: Api = Client.create()) {

    private val apiKey = BuildConfig.TOKEN

    interface ListMovieCallback {
        fun onResponse(response: List<DataItem>)
    }

    interface ListTvShowCallback {
        fun onResponse(response: List<DataItem>)
    }

    interface MovieDetailCallback {
        fun onResponse(response: DataItem)
    }

    interface TvShowDetailCallback {
        fun onResponse(response: DataItem)
    }

    interface SearchMovieCallback {
        fun onResponse(response: List<DataItem>)
    }

    interface SearchTvShowCallback {
        fun onResponse(response: List<DataItem>)
    }

    fun getMovieList(callback: ListMovieCallback) {
        increment()
        retrofit.listMovie(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(call: Call<ItemResponse>, response: Response<ItemResponse>) {
                val final = response.body()?.result
                callback.onResponse(final!!)
                decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                decrement()
            }

        })
    }

    fun getDetailMovie(id: Int, callback: MovieDetailCallback) {
        increment()
        retrofit.detailMovie(id, apiKey).enqueue(object : Callback<DataItem> {
            override fun onResponse(call: Call<DataItem>, response: Response<DataItem>) {
                callback.onResponse(response.body()!!)
                decrement()
            }

            override fun onFailure(call: Call<DataItem>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                decrement()
            }

        })

    }

    fun getTvShowList(callback: ListTvShowCallback) {
        increment()
        retrofit.listTvShow(apiKey).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>,
            ) {
                val final = response.body()?.result
                callback.onResponse(final!!)
                decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                decrement()
            }

        })

    }

    fun getDetailTv(id: Int, callback: TvShowDetailCallback) {
        increment()
        retrofit.detailTvShow(id, apiKey).enqueue(object : Callback<DataItem> {
            override fun onResponse(call: Call<DataItem>, response: Response<DataItem>) {
                callback.onResponse(response.body()!!)
                decrement()
            }

            override fun onFailure(call: Call<DataItem>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                decrement()
            }

        })

    }

    fun searchMovie(query: String, callback: SearchMovieCallback) {
        increment()
        retrofit.searchMovie(apiKey, query).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>,
            ) {
                val final = response.body()?.result
                callback.onResponse(final!!)
                decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                decrement()
            }
        })
    }

    fun searchTvShow(query: String, callback: SearchTvShowCallback) {
        increment()
        retrofit.searchTvShow(apiKey, query).enqueue(object : Callback<ItemResponse> {
            override fun onResponse(
                call: Call<ItemResponse>,
                response: Response<ItemResponse>,
            ) {
                val final = response.body()?.result
                callback.onResponse(final!!)
                decrement()
            }

            override fun onFailure(call: Call<ItemResponse>, t: Throwable) {
                Log.d(TAG, t.printStackTrace().toString())
                decrement()
            }
        })
    }

    companion object {
        private val TAG = RemoteDataSource::class.java.toString()

        @Volatile
        private var INSTANCE: RemoteDataSource? = null

        fun getInstance(retrofit: Api): RemoteDataSource? {
            if (INSTANCE == null) {
                synchronized(DataRepository::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = RemoteDataSource(retrofit)
                }
            }
            return INSTANCE
        }

    }

}