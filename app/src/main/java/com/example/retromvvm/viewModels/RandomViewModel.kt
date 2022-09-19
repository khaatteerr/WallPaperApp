package com.example.retromvvm.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.retromvvm.model.paging.RandomPagingSource
import com.example.retromvvm.repository.MainRepository

class RandomViewModel:ViewModel() {
    private val repository = MainRepository()
    val randomPage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = { RandomPagingSource(repository.retroService()) }
    ).flow.cachedIn(viewModelScope)


}