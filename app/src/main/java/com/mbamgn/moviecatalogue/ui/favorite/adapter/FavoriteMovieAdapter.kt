package com.mbamgn.moviecatalogue.ui.favorite.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.databinding.ItemListMovieBinding
import com.mbamgn.moviecatalogue.ui.detail.DetailActivity
import com.mbamgn.moviecatalogue.utils.Utility.loadImage

class FavoriteMovieAdapter :
    PagingDataAdapter<MovieEntity, FavoriteMovieAdapter.FavMovieViewHolder>(DiffCallbackFavMovie()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavMovieViewHolder {
        val view = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavMovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }

    inner class FavMovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: MovieEntity) {

            binding.apply {
                tvListTitleMovie.text = data.title
                tvListReleaseDateMovie.text = data.releaseDate
                tvListDescMovie.text = data.desc
                imgListPosterMovie.loadImage(data.poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.apply {
                        putExtra(DetailActivity.EXTRA_TYPE, "movie")
                        putExtra(DetailActivity.EXTRA_ID, data.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    private class DiffCallbackFavMovie : DiffUtil.ItemCallback<MovieEntity>() {
        override fun areItemsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: MovieEntity, newItem: MovieEntity): Boolean =
            oldItem.id == newItem.id
    }

}