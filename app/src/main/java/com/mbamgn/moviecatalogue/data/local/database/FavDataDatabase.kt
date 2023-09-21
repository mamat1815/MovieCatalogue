package com.mbamgn.moviecatalogue.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.data.TvShowEntity

@Database(
    entities = [MovieEntity::class,
        TvShowEntity::class], version = 1
)
abstract class FavDataDatabase : RoomDatabase() {
    abstract fun favDataDao(): FavDataDao

    companion object {
        @Volatile
        private var INSTANCE: FavDataDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): FavDataDatabase {
            if (INSTANCE == null) {
                synchronized(FavDataDatabase) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        FavDataDatabase::class.java, "fav_data_database"
                    )
                        .build()
                }
            }
            return INSTANCE as FavDataDatabase
        }
    }

}