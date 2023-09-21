package com.mbamgn.moviecatalogue.data.remote.retrofit

import com.mbamgn.moviecatalogue.BuildConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object Client {

    private fun client(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor {
                val original = it.request()
                val requestBuilder = original.newBuilder()
                val request = requestBuilder.build()
                it.proceed(request)
            }
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build()

    fun create(): Api =
        Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(Api::class.java)

}