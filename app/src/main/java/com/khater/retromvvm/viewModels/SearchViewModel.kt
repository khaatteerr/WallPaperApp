package com.khater.retromvvm.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn

import com.khater.retromvvm.model.paging.SearchPagingSource
import com.khater.retromvvm.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class SearchViewModel: ViewModel() {

    private val repository = MainRepository()

    fun searchFromApi(keyWord:  String ): Flow<PagingData<com.khater.retromvvm.model.domain.Data>> {
        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { SearchPagingSource(repository.retroService(), keyWord) }
        ).flow.cachedIn(viewModelScope)
    }
}