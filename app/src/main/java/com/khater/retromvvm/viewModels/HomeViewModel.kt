package com.khater.retromvvm.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn

import com.khater.retromvvm.model.paging.HomePagingSource
import com.khater.retromvvm.repository.MainRepository

class HomeViewModel : ViewModel() {

    private val repository = MainRepository()


    val homePage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            HomePagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)

}