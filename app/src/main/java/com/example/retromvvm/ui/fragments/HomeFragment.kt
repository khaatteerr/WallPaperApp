package com.example.retromvvm.ui.fragments

import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentHomeBinding
import com.example.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.viewModels.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {

    private val viewModel: HomeViewModel by viewModels()

    override fun initViewModel() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.homePage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallRecyclerView.layoutManager = layoutManager
        binding.wallRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter{recyclerViewAdapter.retry()},
            footer = LoadStateAdapter{recyclerViewAdapter.retry()}
        )
        recyclerViewAdapter.addLoadStateListener {loadState->
            binding.wallRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.buttonRetry.isVisible = loadState.source.refresh is LoadState.Error
            handelError(loadState)
        }
        binding.buttonRetry.setOnClickListener {
            recyclerViewAdapter.retry()
        }

    }

}
