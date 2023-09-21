package com.mbamgn.moviecatalogue.util

import androidx.paging.PagingSource
import androidx.paging.PagingState

class PagingUtil<T : Any>(
    private val data: List<T>
) : PagingSource<Int, T>() {
    override fun getRefreshKey(state: PagingState<Int, T>): Int {
        return 0
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, T> {
        return LoadResult.Page(
            data = data,
            prevKey = null,
            nextKey = null
        )
    }
}
