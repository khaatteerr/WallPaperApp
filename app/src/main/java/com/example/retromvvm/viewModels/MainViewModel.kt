package com.example.retromvvm.viewModels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.retromvvm.model.paging.HomePagingSource
import com.example.retromvvm.model.domain.Data
import com.example.retromvvm.model.paging.CategoryPagingSource
import com.example.retromvvm.model.paging.PopularPagingSource
import com.example.retromvvm.model.paging.RandomPagingSource
import com.example.retromvvm.repository.MainRepository
import com.example.retromvvm.utils.Constants
import kotlinx.coroutines.flow.Flow


class MainViewModel : ViewModel() {

    private val repository = MainRepository()


    fun loadDataParameter(s: String): Flow<PagingData<Data>> {
        return when (s) {
            Constants.HOME -> {
                loadDataToHome()
            }
            Constants.POPULAR -> {
                loadDataToPopular()
            }
            Constants.RANDOM ->{
                loadDataToRandom()
            }


            else -> {throw Exception("NOT FOUND")}
        }
    }

    private fun loadDataToHome(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { HomePagingSource(repository.retroService()) }
        ).flow.cachedIn(viewModelScope)
    }

    private fun loadDataToPopular(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { PopularPagingSource(repository.retroService()) }
        ).flow.cachedIn(viewModelScope)
    }

    private fun loadDataToRandom(): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { RandomPagingSource(repository.retroService()) }
        ).flow.cachedIn(viewModelScope)
    }
      fun loadCategoryToRandom(category:String): Flow<PagingData<Data>> {
        return Pager(config = PagingConfig(pageSize = 30),
            pagingSourceFactory = { CategoryPagingSource(repository.retroService(),category) }
        ).flow.cachedIn(viewModelScope)
    }


}