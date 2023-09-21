package com.mbamgn.moviecatalogue.ui.favorite.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.MovieEntity
import com.mbamgn.moviecatalogue.util.PagingUtil
import com.mbamgn.moviecatalogue.utils.DataDummy
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieFragmentViewModelTest {
    private lateinit var viewModel: FavoriteMovieFragmentViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(DataRepository::class.java)

    private val dataDummy = DataDummy.generateDummyFavMovie()
    private val config = PagingConfig(
        enablePlaceholders = false,
        initialLoadSize = 10,
        pageSize = 10
    )

    @Before
    fun setUp() {
        viewModel = FavoriteMovieFragmentViewModel(repository)

    }

    @Test
    fun getFavMovie() {
        val eqData = PagingUtil(dataDummy)
        val favMovie: Flow<PagingData<MovieEntity>> =
            Pager(config, pagingSourceFactory = { eqData }).flow
        Mockito.`when`(repository.getFavMovie()).thenReturn(favMovie)
        assertNotNull(viewModel.getFavMovie())
        assertEquals(favMovie, viewModel.getFavMovie())
    }
}