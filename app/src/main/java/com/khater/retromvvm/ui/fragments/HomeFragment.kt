package com.khater.retromvvm.ui.fragments


import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.khater.retromvvm.databinding.FragmentHomeBinding

import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.HomeViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate,
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun initViewModel() {

        lifecycleScope.launch {
            viewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }


    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallRecyclerView.layoutManager = layoutManager
        binding.wallRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { recyclerViewAdapter.retry() },
            footer = LoadStateAdapter { recyclerViewAdapter.retry() }
        )
        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.wallRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }
        binding.buttonRetry.setOnClickListener {
            recyclerViewAdapter.retry()
        }

    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(
        Constants.NavigationIntent.FromMainToDownload
    )


}
