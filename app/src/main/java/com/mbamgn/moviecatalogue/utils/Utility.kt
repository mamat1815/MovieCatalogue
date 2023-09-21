package com.mbamgn.moviecatalogue.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.mbamgn.moviecatalogue.R

object Utility {
    fun ImageView.loadImage(path: String?) {
        Glide.with(context)
            .load("https://image.tmdb.org/t/p/w500${path}")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_loading))
            .error(R.drawable.ic_eror)
            .into(this)
    }
}