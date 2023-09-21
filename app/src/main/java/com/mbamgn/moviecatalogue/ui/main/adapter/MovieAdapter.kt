package com.mbamgn.moviecatalogue.ui.main.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.databinding.ItemListMovieBinding
import com.mbamgn.moviecatalogue.ui.detail.DetailActivity
import com.mbamgn.moviecatalogue.utils.Utility.loadImage

class MovieAdapter : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    private val listMovie = ArrayList<DataItem>()

    fun setMovieData(data: List<DataItem>) {
        val diffCallback = DiffCallbackMain(this.listMovie, data)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listMovie.apply {
            clear()
            addAll(data)
        }
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): MovieViewHolder {
        val view = ItemListMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.bind(listMovie[position])

    override fun getItemCount(): Int = listMovie.size

    inner class MovieViewHolder(private val binding: ItemListMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: DataItem) {
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
}