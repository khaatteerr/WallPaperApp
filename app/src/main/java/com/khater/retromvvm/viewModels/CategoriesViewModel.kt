package com.khater.retromvvm.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.khater.retromvvm.model.domain.Data
import com.khater.retromvvm.model.paging.CategoryPagingSource
import com.khater.retromvvm.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CategoriesViewModel constructor(private var categoryID: String) : ViewModel() {
    private val repository = MainRepository()

    var data: MutableLiveData<PagingData<Data>> = MutableLiveData()

    init {
        viewModelScope.launch {
            loadCategoryToRandom(categoryID).collect {
                data.postValue(it)
            }
        }
    }

    private fun loadCategoryToRandom(category: String): Flow<PagingData<Data>> {

        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { CategoryPagingSource(repository.retroService(), category) }
        ).flow.cachedIn(viewModelScope)
    }

}