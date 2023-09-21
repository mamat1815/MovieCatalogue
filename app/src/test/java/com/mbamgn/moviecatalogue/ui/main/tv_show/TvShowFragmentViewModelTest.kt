package com.mbamgn.moviecatalogue.ui.main.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.utils.DataDummy
import com.mbamgn.moviecatalogue.utils.LiveDataTest
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvShowFragmentViewModelTest {

    private lateinit var viewModel: TvShowFragmentViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<DataItem>>

    @Before
    fun setUp() {
        viewModel = TvShowFragmentViewModel(repository)
    }

    private val dummyTvShow = DataDummy.generateDummyTvShow()
    private val tvShow = MutableLiveData<List<DataItem>>()
    private val query = "peacemaker"

    @Test
    fun getListTvShow() {

        tvShow.value = dummyTvShow
        `when`(repository.getTvShow()).thenReturn(tvShow)
        val tvShowEntities = LiveDataTest.getValue(repository.getTvShow())
        verify(repository).getTvShow()
        assertNotNull(tvShowEntities)
        assertEquals(dummyTvShow.size.toLong(), tvShowEntities.size.toLong())
        viewModel.getListTvShow().observeForever(observer)
        verify(observer).onChanged(dummyTvShow)

    }

    @Test
    fun searchTvShow() {
        tvShow.value = dummyTvShow
        `when`(repository.searchTvShow(query)).thenReturn(tvShow)
        val searchEntities = LiveDataTest.getValue(repository.searchTvShow(query))
        verify(repository).searchTvShow(query)
        assertNotNull(searchEntities)
        assertEquals(dummyTvShow.size.toLong(), searchEntities.size.toLong())
        viewModel.searchTvShow(query).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)
    }

}