package com.example.retromvvm.ui.fragments

import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentRandomBinding
import com.example.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.viewModels.RandomViewModel
import com.google.android.gms.ads.AdRequest
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




}