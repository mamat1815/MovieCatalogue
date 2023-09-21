package com.mbamgn.moviecatalogue.ui.main.adapter

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.mbamgn.moviecatalogue.ui.main.MainActivity
import com.mbamgn.moviecatalogue.ui.main.movie.MovieFragment
import com.mbamgn.moviecatalogue.ui.main.tv_show.TvShowFragment

class MainViewPager(activity: AppCompatActivity) : FragmentStateAdapter(activity) {
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> MovieFragment()
            1 -> TvShowFragment()
            else -> Fragment()
        }
    }

    override fun getItemCount(): Int = MainActivity.TAB_TITLES.size

}