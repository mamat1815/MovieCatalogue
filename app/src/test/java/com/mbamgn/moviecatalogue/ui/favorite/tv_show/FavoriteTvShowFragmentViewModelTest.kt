package com.mbamgn.moviecatalogue.ui.favorite.tv_show

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.mbamgn.moviecatalogue.data.DataRepository
import com.mbamgn.moviecatalogue.data.TvShowEntity
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
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavoriteTvShowFragmentViewModelTest {
    private lateinit var viewModel: FavoriteTvShowFragmentViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val repository = Mockito.mock(DataRepository::class.java)

    private val dataDummy = DataDummy.generateDummyFavTvShow()
    private val config = PagingConfig(
        enablePlaceholders = false,
        initialLoadSize = 10,
        pageSize = 10
    )

    @Before
    fun setUp() {
        viewModel = FavoriteTvShowFragmentViewModel(repository)

    }

    @Test
    fun getFavMovie() {
        val eqData = PagingUtil(dataDummy)
        val favTvShow: Flow<PagingData<TvShowEntity>> =
            Pager(config, pagingSourceFactory = { eqData }).flow
        `when`(repository.getFavTvShow()).thenReturn(favTvShow)
        assertNotNull(viewModel.getFavTvShow())
        assertEquals(favTvShow, viewModel.getFavTvShow())
    }
}