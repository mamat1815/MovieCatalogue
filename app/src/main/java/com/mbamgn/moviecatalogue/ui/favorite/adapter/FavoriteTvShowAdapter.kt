package com.mbamgn.moviecatalogue.ui.favorite.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbamgn.moviecatalogue.data.TvShowEntity
import com.mbamgn.moviecatalogue.databinding.ItemListTvShowBinding
import com.mbamgn.moviecatalogue.ui.detail.DetailActivity
import com.mbamgn.moviecatalogue.utils.Utility.loadImage

class FavoriteTvShowAdapter :
    PagingDataAdapter<TvShowEntity, FavoriteTvShowAdapter.FavTvShowViewHolder>(DiffCallbackFavTvShow()) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): FavTvShowViewHolder {
        val view = ItemListTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavTvShowViewHolder, position: Int) =
        holder.bind(getItem(position)!!)

    inner class FavTvShowViewHolder(private val binding: ItemListTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: TvShowEntity) {
            binding.apply {
                tvListTitleTvShow.text = data.name
                tvListAirDateTvShow.text = data.airDate
                tvListDescTvShow.text = data.desc
                imgListPosterTvShow.loadImage(data.poster)

                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.apply {
                        putExtra(DetailActivity.EXTRA_TYPE, "tv_show")
                        putExtra(DetailActivity.EXTRA_ID, data.id)
                    }
                    itemView.context.startActivity(intent)
                }
            }
        }
    }

    private class DiffCallbackFavTvShow : DiffUtil.ItemCallback<TvShowEntity>() {
        override fun areItemsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: TvShowEntity, newItem: TvShowEntity): Boolean =
            oldItem.id == newItem.id

    }
}

