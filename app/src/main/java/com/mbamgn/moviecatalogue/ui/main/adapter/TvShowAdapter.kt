package com.mbamgn.moviecatalogue.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.databinding.ItemListTvShowBinding
import com.mbamgn.moviecatalogue.ui.detail.DetailActivity
import com.mbamgn.moviecatalogue.utils.Utility.loadImage

class TvShowAdapter : RecyclerView.Adapter<TvShowAdapter.TvShowViewHolder>() {

    private val listTvShow = ArrayList<DataItem>()

    fun setTvShowData(data: List<DataItem>) {
        val diffCallback = DiffCallbackMain(this.listTvShow, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listTvShow.apply {
            clear()
            addAll(data)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): TvShowViewHolder {
        val view = ItemListTvShowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TvShowViewHolder, position: Int) =
        holder.bind(listTvShow[position])

    override fun getItemCount(): Int = listTvShow.size

    inner class TvShowViewHolder(private val binding: ItemListTvShowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: DataItem) {
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

}