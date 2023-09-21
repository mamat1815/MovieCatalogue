package com.mbamgn.moviecatalogue.di

import android.content.Context
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.local.LocalDataSource
import com.mbamgn.moviecatalogue.data.local.database.FavDataDatabase
import com.mbamgn.moviecatalogue.data.remote.RemoteDataSource
import com.mbamgn.moviecatalogue.data.remote.retrofit.Client

object Injection {

    fun repository(context: Context): DataRepository {
        val db = FavDataDatabase.getDatabase(context)
        val remoteDataSource = RemoteDataSource.getInstance(Client.create())
        val localDataSource = LocalDataSource.getInstance(db.favDataDao())

        return remoteDataSource?.let { DataRepository.getInstance(it, localDataSource) }!!
    }

}