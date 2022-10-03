package com.khater.retromvvm.ui.fragments

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.khater.retromvvm.databinding.FragmentSearchBinding
import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.SearchViewModel
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class SearchFragment :  BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate

) {

    val viewModel : SearchViewModel by viewModels()

    override var recyclerViewAdapter : RecyclerViewAdapter = RecyclerViewAdapter(Constants.NavigationIntent.FromSearchToDownload)

    override fun initViewModel(){

        Observable.create { emitter ->
            binding.searchEditText.doOnTextChanged { text, _, _, _ ->
                emitter.onNext(text.toString())
            }
        }.debounce(500, TimeUnit.MILLISECONDS)
            .subscribe(
                { t ->
                    lifecycleScope.launch(Dispatchers.IO) {
                        viewModel.searchFromApi(t).collect {
                            recyclerViewAdapter.submitData(it)
                        }
                    }
                }, { e ->
                    Toast.makeText(context, "${e.message}", Toast.LENGTH_LONG).show()
                }
            )
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.searchRecyclerView.layoutManager = layoutManager
        binding.searchRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { recyclerViewAdapter.retry() },
            footer = LoadStateAdapter { recyclerViewAdapter.retry() }
        )
        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.searchRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.searchProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            handelError(loadState)
        }
    }


//    private fun initRecyclerView() {
//        val layoutManager = GridLayoutManager(context, 3)
//        binding.searchRecyclerView.layoutManager = layoutManager
//        binding.searchRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
//            header = LoadStateAdapter { recyclerViewAdapter.retry() },
//            footer = LoadStateAdapter { recyclerViewAdapter.retry() }
//        )
//
//        recyclerViewAdapter.addLoadStateListener { loadState ->
//            binding.searchRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
//            binding.searchProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
//            handelError(loadState)
//
//        }
//    }
//
//    private fun handelError(loadStates: CombinedLoadStates) {
//        val errorState = loadStates.source.append as? LoadState.Error
//            ?: loadStates.source.prepend as? LoadState.Error
//
//        errorState?.let {
//            Toast.makeText(context, "${it.error}", Toast.LENGTH_LONG).show()
//        }
//
//    }
//
//
//    private fun initViewModel() {
//
//
//        val observable = Observable.create { emitter ->
//            binding.searchEditText.doOnTextChanged { text, _, _, _ ->
//                emitter.onNext(text.toString())
//            }
//        }.debounce(500, TimeUnit.MILLISECONDS)
//
//        observable.subscribe(
//            { t ->
//
//                lifecycleScope.launch(Dispatchers.IO) {
//                    viewModel.searchFromApi(t).collect {
//                        recyclerViewAdapter.submitData(it)
//                    }
//                }
//            }, { e ->
//                Toast.makeText(context, "${e.message}", Toast.LENGTH_LONG).show()
//            }
//        )
//    }

}