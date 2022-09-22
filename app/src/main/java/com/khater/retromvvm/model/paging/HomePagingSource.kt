package com.khater.retromvvm.model.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.khater.retromvvm.model.domain.Data
 import com.khater.retromvvm.model.networking.RetroService

class HomePagingSource(
    private val apiService:
    RetroService
) :
    PagingSource<Int,  Data>() {
    override fun getRefreshKey(state: PagingState<Int,  Data>): Int? {
        return state.anchorPosition
    }


    override suspend fun load(params: LoadParams<Int>): LoadResult<Int,  Data> {
        return try {
            val nextPage = params.key ?: FIRST_PAGE_INDEX
            val responsePopular = apiService.getHomeFromApi(nextPage)

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