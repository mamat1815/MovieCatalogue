package com.mbamgn.moviecatalogue.data

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "data_item")
data class DataItem(

    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var poster: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdrop: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var desc: String?,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    var tagline: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var rate: Float,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String?,

    //For TvShow
    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?,

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    var airDate: String?,

    ) : Parcelable

data class ItemResponse(
    @SerializedName("results")
    var result: List<DataItem>,
)

@Parcelize
@Entity(tableName = "tv_show")
data class TvShowEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var poster: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdrop: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var desc: String?,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    var tagline: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var rate: Float,

    @ColumnInfo(name = "first_air_date")
    @SerializedName("first_air_date")
    var airDate: String?,

    ) : Parcelable

@Parcelize
@Entity(tableName = "movie")
data class MovieEntity(

    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    @SerializedName("id")
    var id: Int,

    @ColumnInfo(name = "title")
    @SerializedName("title")
    var title: String?,

    @ColumnInfo(name = "poster_path")
    @SerializedName("poster_path")
    var poster: String?,

    @ColumnInfo(name = "backdrop_path")
    @SerializedName("backdrop_path")
    var backdrop: String?,

    @ColumnInfo(name = "overview")
    @SerializedName("overview")
    var desc: String?,

    @ColumnInfo(name = "tagline")
    @SerializedName("tagline")
    var tagline: String?,

    @ColumnInfo(name = "vote_average")
    @SerializedName("vote_average")
    var rate: Float,

    @ColumnInfo(name = "release_date")
    @SerializedName("release_date")
    var releaseDate: String?,

    ) : Parcelable

