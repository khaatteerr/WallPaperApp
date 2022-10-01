package com.khater.retromvvm.ui.fragments

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.khater.retromvvm.databinding.FragmentPopularBinding

 import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.PopularViewModel
 import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment : BaseFragment<FragmentPopularBinding>(
    FragmentPopularBinding::inflate
) {
    private val viewModel: PopularViewModel by viewModels()

    override fun initViewModel() {
        lifecycleScope.launch  {
            viewModel.popularPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }

    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallRecyclerViewPopular.layoutManager = layoutManager
        binding.wallRecyclerViewPopular.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { recyclerViewAdapter.retry() },
            footer = LoadStateAdapter { recyclerViewAdapter.retry() }
        )
        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.wallRecyclerViewPopular.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            binding.popularProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.popularButtonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }
        binding.popularButtonRetry.setOnClickListener {
            recyclerViewAdapter.retry()
        }
    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(
        Constants.NavigationIntent.FromMainToDownload
    )


}