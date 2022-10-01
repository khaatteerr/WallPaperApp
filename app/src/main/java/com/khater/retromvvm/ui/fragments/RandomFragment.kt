package com.khater.retromvvm.ui.fragments

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.khater.retromvvm.databinding.FragmentRandomBinding

import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.RandomViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class RandomFragment : BaseFragment<FragmentRandomBinding>(
    FragmentRandomBinding::inflate
) {


    private val viewModel: RandomViewModel by viewModels()
    override fun initViewModel() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.randomPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }


    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.randomRecyclerView.layoutManager = layoutManager
        binding.randomRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter{recyclerViewAdapter.retry()},
            footer = LoadStateAdapter{recyclerViewAdapter.retry()}
        )
        recyclerViewAdapter.addLoadStateListener {loadState->
            binding.randomRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.RandomProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.randomButtonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }
        binding.randomButtonRetry.setOnClickListener {
            recyclerViewAdapter.retry()
        }
    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(
        Constants.NavigationIntent.FromMainToDownload
    )


}