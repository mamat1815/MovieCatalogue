package com.mbamgn.moviecatalogue.ui.favorite

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.databinding.ActivityFavoriteBinding
import com.mbamgn.moviecatalogue.ui.favorite.adapter.FavoriteViewPager
import com.mbamgn.moviecatalogue.ui.main.MainActivity

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            val favPager = FavoriteViewPager(this@FavoriteActivity)
            vpFavorite.adapter = favPager
            TabLayoutMediator(tabsFavorite, vpFavorite) { tabsMain, position ->
                tabsMain.text = resources.getString(MainActivity.TAB_TITLES[position])
            }.attach()

            //ToolBar
            tvToolbarTitleFavorite.text = resources.getString(R.string.fav_catalogue)
            setSupportActionBar(toolbarFavorite)
            supportActionBar?.setDisplayShowTitleEnabled(false)
            toolbarFavorite.setNavigationOnClickListener {
                onBackPressed()
            }

        }

    }

    companion object {
        val TAB_TITLES = intArrayOf(
            R.string.movie,
            R.string.tv_show
        )
    }
}