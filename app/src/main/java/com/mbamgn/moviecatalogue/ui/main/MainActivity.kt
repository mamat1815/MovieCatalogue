package com.mbamgn.moviecatalogue.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.mbamgn.moviecatalogue.R
import com.mbamgn.moviecatalogue.databinding.ActivityMainBinding
import com.mbamgn.moviecatalogue.ui.favorite.FavoriteActivity
import com.mbamgn.moviecatalogue.ui.main.adapter.MainViewPager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {

            val mainPager = MainViewPager(this@MainActivity)
            vpMain.adapter = mainPager
            TabLayoutMediator(tabsMain, vpMain) { tabsMain, position ->
                tabsMain.text = resources.getString(TAB_TITLES[position])
            }.attach()

            //ToolBar
            tvToolbarTitleMain.text = resources.getString(R.string.app_name)

            //Fab Fav
            fabFav.setOnClickListener {
                val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                startActivity(intent)
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