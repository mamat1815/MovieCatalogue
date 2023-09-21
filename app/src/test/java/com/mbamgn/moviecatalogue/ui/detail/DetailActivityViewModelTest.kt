package com.mbamgn.moviecatalogue.ui.detail

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
class DetailActivityViewModelTest {

    private lateinit var viewModel: DetailActivityViewModel
    private val dummyMovie = DataDummy.generateDummyMovie()[0]
    private val dummyTvShow = DataDummy.generateDummyTvShow()[0]
    private val movieId = dummyMovie.id
    private val movieType = "movie"
    private val tvShowId = dummyTvShow.id
    private val tvShowType = "tv_show"

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: DataRepository

    @Mock
    private lateinit var observer: Observer<DataItem>

    @Before
    fun setUp() {
        viewModel = DetailActivityViewModel(repository)
    }

    @Test
    fun getDetailMovie() {
        viewModel.getDetailData(movieType, movieId)

        val detailMovie = MutableLiveData<DataItem>()
        detailMovie.value = dummyMovie
        `when`(repository.getDetailData(movieId, movieType)).thenReturn(detailMovie)
        verify(repository).getDetailData(movieId, movieType)

        val dataMovieEntities = LiveDataTest.getValue(repository.getDetailData(movieId, movieType))
        assertNotNull(dataMovieEntities)
        viewModel.getDetailData(movieType, movieId).observeForever(observer)
        verify(observer).onChanged(dummyMovie)

        val dataMovie = detailMovie.value
        assertEquals(dataMovie?.id, viewModel.getDetailData(movieType, movieId).value?.id)
        assertEquals(dataMovie?.title, viewModel.getDetailData(movieType, movieId).value?.title)
        assertEquals(dataMovie?.poster, viewModel.getDetailData(movieType, movieId).value?.poster)
        assertEquals(
            dataMovie?.backdrop,
            viewModel.getDetailData(movieType, movieId).value?.backdrop
        )
        assertEquals(dataMovie?.desc, viewModel.getDetailData(movieType, movieId).value?.desc)
        assertEquals(dataMovie?.tagline, viewModel.getDetailData(movieType, movieId).value?.tagline)
        assertEquals(dataMovie?.rate, viewModel.getDetailData(movieType, movieId).value?.rate)
        assertEquals(
            dataMovie?.releaseDate,
            viewModel.getDetailData(movieType, movieId).value?.releaseDate
        )
    }

    @Test
    fun getDetailTvShow() {
        viewModel.getDetailData(tvShowType, tvShowId)

        val detailTvShow = MutableLiveData<DataItem>()
        detailTvShow.value = dummyTvShow
        `when`(repository.getDetailData(tvShowId, tvShowType)).thenReturn(detailTvShow)
        verify(repository).getDetailData(tvShowId, tvShowType)

        val dataTvShowEntities =
            LiveDataTest.getValue(repository.getDetailData(tvShowId, tvShowType))
        assertNotNull(dataTvShowEntities)
        viewModel.getDetailData(tvShowType, tvShowId).observeForever(observer)
        verify(observer).onChanged(dummyTvShow)

        val dataTvShow = detailTvShow.value
        assertEquals(dataTvShow?.id, viewModel.getDetailData(tvShowType, tvShowId).value?.id)
        assertEquals(dataTvShow?.name, viewModel.getDetailData(tvShowType, tvShowId).value?.name)
        assertEquals(
            dataTvShow?.poster,
            viewModel.getDetailData(tvShowType, tvShowId).value?.poster
        )
        assertEquals(
            dataTvShow?.backdrop,
            viewModel.getDetailData(tvShowType, tvShowId).value?.backdrop
        )
        assertEquals(dataTvShow?.desc, viewModel.getDetailData(tvShowType, tvShowId).value?.desc)
        assertEquals(
            dataTvShow?.tagline,
            viewModel.getDetailData(tvShowType, tvShowId).value?.tagline
        )
        assertEquals(dataTvShow?.rate, viewModel.getDetailData(tvShowType, tvShowId).value?.rate)
        assertEquals(
            dataTvShow?.airDate,
            viewModel.getDetailData(tvShowType, tvShowId).value?.airDate
        )
    }
}