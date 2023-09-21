package com.mbamgn.moviecatalogue.data.source.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mbamgn.moviecatalogue.data.DataItem
import com.mbamgn.moviecatalogue.data.local.LocalDataSource
import com.mbamgn.moviecatalogue.data.remote.RemoteDataSource
import com.mbamgn.moviecatalogue.data.remote.RemoteDataSource.*
import com.mbamgn.moviecatalogue.util.PagingUtil
import com.mbamgn.moviecatalogue.utils.DataDummy
import com.mbamgn.moviecatalogue.utils.LiveDataTest
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.eq
import com.nhaarman.mockitokotlin2.verify
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class DataRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var observer: Observer<DataItem>

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val local = Mockito.mock(LocalDataSource::class.java)

    private val repository = FakeDataRepository(remote, local)

    private val responseMovie = DataDummy.generateDummyMovie()
    private val responseTvShow = DataDummy.generateDummyTvShow()
    private val idMovie = responseMovie[0].id
    private val idTvShow = responseTvShow[0].id
    private val localMovie = DataDummy.generateDummyFavMovie()
    private val localTvShow = DataDummy.generateDummyFavTvShow()
    private val typeMovie = "movie"
    private val typeTvShow = "tv_show"
    private val searchQuery = "Spiderman"

    @Test
    fun getListMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as ListMovieCallback)
                .onResponse(responseMovie)
            null
        }.`when`(remote).getMovieList(any())
        val movieEntities = LiveDataTest.getValue(repository.getMovie())
        verify(remote).getMovieList(any())
        assertNotNull(movieEntities)
        assertEquals(responseMovie, movieEntities)
    }

    @Test
    fun searchMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as SearchMovieCallback)
                .onResponse(responseMovie)
            null
        }.`when`(remote).searchMovie(eq(searchQuery), any())
        val searchEntities = LiveDataTest.getValue(repository.searchMovie(searchQuery))
        verify(remote).searchMovie(eq(searchQuery), any())
        assertNotNull(searchEntities)
        assertEquals(responseMovie, searchEntities)
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[1] as MovieDetailCallback)
                .onResponse(responseMovie[0])
            null
        }.`when`(remote).getDetailMovie(eq(idMovie), any())

        val liveDataMovie = LiveDataTest.getValue(repository.getDetailData(idMovie, typeMovie))
        repository.getDetailData(idMovie, typeMovie).observeForever(observer)
        verify(observer).onChanged(responseMovie[0])
        assertNotNull(liveDataMovie)
        assertEquals(idMovie, liveDataMovie.id)
    }

    @Test
    fun getFavMovie() {
        repository.setFavMovie(localMovie[0])
        repository.counterFavMovie(localMovie[0].id)
        verify(local).insertFavMovie(localMovie[0])
        verify(local).checkFavMovie(localMovie[0].id)
        runBlocking {
            val eqData = PagingUtil(localMovie)
            `when`(local.getFavMovie()).thenReturn(eqData)
            assertEquals(eqData, local.getFavMovie())
        }
        val getFavMovie = repository.getFavMovie()
        assertNotNull(getFavMovie)
        repository.deleteFavMovie(localMovie[0])
    }

    @Test
    fun getTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[0] as ListTvShowCallback)
                .onResponse(responseTvShow)
            null
        }.`when`(remote).getTvShowList(any())
        val tvShowEntities = LiveDataTest.getValue(repository.getTvShow())
        verify(remote).getTvShowList(any())
        assertNotNull(tvShowEntities)
        assertEquals(responseTvShow, tvShowEntities)
    }

    @Test
    fun searchTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as SearchTvShowCallback)
                .onResponse(responseTvShow)
            null
        }.`when`(remote).searchTvShow(eq(searchQuery), any())
        val searchEntities = LiveDataTest.getValue(repository.searchTvShow(searchQuery))
        verify(remote).searchTvShow(eq(searchQuery), any())
        assertNotNull(searchEntities)
        assertEquals(responseTvShow, searchEntities)
    }

    @Test
    fun getDetailTvShow() {
        doAnswer { invocation ->
            (invocation.arguments[1] as TvShowDetailCallback)
                .onResponse(responseTvShow[0])
            null
        }.`when`(remote).getDetailTv(eq(idTvShow), any())

        val liveDataTvShow = LiveDataTest.getValue(repository.getDetailData(idTvShow, typeTvShow))

        repository.getDetailData(idTvShow, typeTvShow).observeForever(observer)
        verify(observer).onChanged(responseTvShow[0])
        assertNotNull(liveDataTvShow)
        assertEquals(idTvShow, liveDataTvShow.id)
    }

    @Test
    fun getFavTvShow() {
        repository.setFavTvShow(localTvShow[0])
        repository.counterFavTvShow(localTvShow[0].id)
        verify(local).insertTvShow(localTvShow[0])
        verify(local).checkFavTvShow(localTvShow[0].id)
        runBlocking {
            val eqData = PagingUtil(localTvShow)
            `when`(local.getFavTvShow()).thenReturn(eqData)
            assertEquals(eqData, local.getFavTvShow())
        }
        val getFavTvShow = repository.getFavTvShow()
        assertNotNull(getFavTvShow)
    }

}