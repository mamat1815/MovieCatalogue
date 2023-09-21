package com.mbamgn.moviecatalogue.ui.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.data.TvShowEntity
import com.mbamgn.moviecatalogue.databinding.ActivityDetailMovieBinding
import com.mbamgn.moviecatalogue.utils.Utility.loadImage
import com.mbamgn.moviecatalogue.utils.ViewModelFactory

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailMovieBinding
    private lateinit var type: String
    private lateinit var movieEntity: MovieEntity
    private lateinit var tvShowEntity: TvShowEntity
    private var isChecked: Boolean = false
    private val typeData = "movie"

    private val viewModel by lazy {
        val viewModelFactory = application.let { ViewModelFactory.getInstance(this) }
        viewModelFactory.let {
            ViewModelProvider(
                this,
                it
            ).get(DetailActivityViewModel::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailMovieBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra(EXTRA_ID, 0)
        type = intent.getStringExtra(EXTRA_TYPE)!!

        binding.apply {

            //ViewModel
            viewModel.apply {
                getDetailData(type, id).observe(this@DetailActivity) { data ->
                    detailData(data)
                    if (type == typeData) {
                        movieEntity = MovieEntity(
                            data.id,
                            data.title,
                            data.poster,
                            data.backdrop,
                            data.desc,
                            data.tagline,
                            data.rate,
                            data.releaseDate
                        )
                        counterFavMovie(data.id)
                    } else {
                        tvShowEntity = TvShowEntity(
                            data.id,
                            data.name,
                            data.poster,
                            data.backdrop,
                            data.desc,
                            data.tagline,
                            data.rate,
                            data.airDate
                        )
                        counterFavTvShow(data.id)
                    }

                }

                getLoad().observe(this@DetailActivity) {
                    setLoad(it)
                }

                isFav().observe(this@DetailActivity) {
                    isChecked = it
                    tgFav.isChecked = it == true
                }

            }

            //Set Fav Data
            if (type == typeData) {
                tgFav.setOnClickListener {
                    isChecked = !isChecked
                    if (isChecked) {
                        viewModel.setFavMovie(movieEntity)
                        tgFav.isChecked = true
                        removeToast(resources.getString(R.string.add))

                    } else {
                        viewModel.deleteFavMovie(movieEntity)
                        tgFav.isChecked = false
                        removeToast(resources.getString(R.string.delete))
                    }
                }
            } else {
                tgFav.setOnClickListener {
                    isChecked = !isChecked
                    if (isChecked) {
                        viewModel.setFavTvShow(tvShowEntity)
                        tgFav.isChecked = true
                        removeToast(resources.getString(R.string.add))

                    } else {
                        viewModel.deleteFavTvShow(tvShowEntity)
                        tgFav.isChecked = false
                        removeToast(resources.getString(R.string.delete))
                    }
                }
            }

            //ToolBar
            setSupportActionBar(toolbarDetailMovie)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            toolbarDetailMovie.setNavigationOnClickListener {
                onBackPressed()
            }

        }

    }

    private fun removeToast(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

    private fun setLoad(state: Boolean) {
        binding.pbDetail.visibility = if (state) View.VISIBLE else View.INVISIBLE
    }

    private fun detailData(data: DataItem) {
        binding.apply {
            if (typeData == type) {
                tvDetailTitle.text = data.title
            } else {
                tvDetailTitle.text = data.name
            }

            val percent = data.rate * 10
            percentDetail.setProgress(percent, false)

            if (data.desc.isNullOrEmpty()) {
                tvDetailDesc.text = resources.getString(R.string.no_desc)
            } else {
                tvDetailDesc.text = data.desc
            }

            if (data.tagline.isNullOrEmpty()) {
                tvDetailTag.text = resources.getString(R.string.no_tag)
            } else {
                tvDetailTag.text = data.tagline
            }

            imgDetailPoster.loadImage(data.poster)

            bgDetailToolbar.loadImage(data.backdrop)
        }

    }

    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_TYPE = "extra_type"
    }
}

