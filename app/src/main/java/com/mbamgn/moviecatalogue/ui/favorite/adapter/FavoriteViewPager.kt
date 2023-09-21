package com.mbamgn.moviecatalogue.ui.favorite.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mbamgn.moviecatalogue.ui.favorite.FavoriteActivity
import com.mbamgn.moviecatalogue.ui.favorite.movie.FavoriteMovieFragment
import com.mbamgn.moviecatalogue.ui.favorite.tv_show.FavoriteTvShowFragment

class FavoriteViewPager(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FavoriteMovieFragment()
            1 -> FavoriteTvShowFragment()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int = FavoriteActivity.TAB_TITLES.size

}