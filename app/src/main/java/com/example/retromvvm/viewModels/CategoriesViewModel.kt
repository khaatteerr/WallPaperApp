package com.example.retromvvm.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.retromvvm.model.domain.Data
import com.example.retromvvm.model.paging.CategoryPagingSource
import com.example.retromvvm.repository.MainRepository
import kotlinx.coroutines.flow.Flow

class CategoriesViewModel  : ViewModel(){
    private val repository = MainRepository()

    fun loadCategoryToRandom(category: String): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { CategoryPagingSource(repository.retroService(), category) }
        ).flow.cachedIn(viewModelScope)
    }

}