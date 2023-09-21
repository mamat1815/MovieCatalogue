package com.mbamgn.moviecatalogue.ui.main.movie

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
class MovieFragmentViewModelTest {

    private lateinit var viewModel: MovieFragmentViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: DataRepository

    @Mock
    private lateinit var observer: Observer<List<DataItem>>
    private val dummyMovie = DataDummy.generateDummyMovie()
    private val movie = MutableLiveData<List<DataItem>>()
    private val query = "Spiderman"

    @Before
    fun setUp() {
        viewModel = MovieFragmentViewModel(repository)
    }

    @Test
    fun getListMovie() {
        movie.value = dummyMovie
        `when`(repository.getMovie()).thenReturn(movie)
        val movieEntities = LiveDataTest.getValue(repository.getMovie())
        verify(repository).getMovie()
        assertNotNull(movieEntities)
        assertEquals(dummyMovie.size.toLong(), movieEntities.size.toLong())
        viewModel.getListMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovie)
    }

    @Test
    fun searchMovie() {
        movie.value = dummyMovie
        `when`(repository.searchMovie(query)).thenReturn(movie)
        val searchEntities = LiveDataTest.getValue(repository.searchMovie(query))
        verify(repository).searchMovie(query)
        assertNotNull(searchEntities)
        assertEquals(dummyMovie.size.toLong(), searchEntities.size.toLong())
        viewModel.searchMovie(query).observeForever(observer)
        verify(observer).onChanged(dummyMovie)

    }
}