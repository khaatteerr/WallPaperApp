package com.example.retromvvm.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.retromvvm.model.paging.HomePagingSource
import com.example.retromvvm.repository.MainRepository
import kotlinx.coroutines.flow.MutableStateFlow

class HomeViewModel : ViewModel() {

    private val repository = MainRepository()

    private val flow = MutableStateFlow(repository.retroService())

    val homePage = Pager(config = PagingConfig(pageSize = 30),
        pagingSourceFactory = {
            HomePagingSource(repository.retroService())
        }
    ).flow.cachedIn(viewModelScope)

}