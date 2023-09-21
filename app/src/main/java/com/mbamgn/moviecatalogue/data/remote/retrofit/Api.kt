package com.mbamgn.moviecatalogue.data.remote.retrofit

import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.ItemResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("/3/movie/popular")
    fun listMovie(@Query("api_key") apiKey: String): Call<ItemResponse>

    @GET("/3/movie/{movie_id}")
    fun detailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String,
    ): Call<DataItem>

    @GET("/3/tv/popular")
    fun listTvShow(@Query("api_key") apiKey: String): Call<ItemResponse>

    @GET("/3/tv/{tv_id}")
    fun detailTvShow(
        @Path("tv_id") id: Int,
        @Query("api_key") apiKey: String,
    ): Call<DataItem>

    @GET("/3/search/movie")
    fun searchMovie(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
    ): Call<ItemResponse>

    @GET("/3/search/tv")
    fun searchTvShow(
        @Query("api_key") api_key: String,
        @Query("query") query: String,
    ): Call<ItemResponse>

}