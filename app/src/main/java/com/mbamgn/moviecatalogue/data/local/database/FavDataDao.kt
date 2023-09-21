package com.mbamgn.moviecatalogue.data.local.database

import androidx.paging.PagingSource
import androidx.room.*
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.data.TvShowEntity

@Dao
interface FavDataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(data: MovieEntity)


    @Query("SELECT * from movie ORDER BY id ASC")
    fun getFavMovie(): PagingSource<Int, MovieEntity>

    @Query("SELECT * from movie WHERE id= :id")
    fun getMovieCount(id: Int): MovieEntity?

    @Delete
    fun deleteMovie(data: MovieEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTvShow(data: TvShowEntity)

    @Query("SELECT * from tv_show ORDER BY id ASC")
    fun getFavTvShow(): PagingSource<Int, TvShowEntity>

    @Query("SELECT * from tv_show WHERE id= :id")
    fun getTvShowCount(id: Int): TvShowEntity?

    @Delete
    fun deleteTvShow(data: TvShowEntity)

}