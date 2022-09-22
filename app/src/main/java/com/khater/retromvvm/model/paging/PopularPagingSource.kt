package com.khater.retromvvm.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.khater.retromvvm.model.domain.Data
 import com.khater.retromvvm.model.networking.RetroService

class PopularPagingSource(private val apiService: RetroService) :
    PagingSource<Int,  Data>() {
    override fun getRefreshKey(state: PagingState<Int,  Data>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Data> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val responsePopular = apiService.getPopularFromApi(nextPage)

            LoadResult.Page(
                data = responsePopular.data,
                prevKey = null,
                nextKey = responsePopular.pagination?.next?.page,
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 1
    }

}