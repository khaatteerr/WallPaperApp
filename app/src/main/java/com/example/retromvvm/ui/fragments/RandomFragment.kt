package com.example.retromvvm.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentRandomBinding
import com.example.retromvvm.model.paging.LoaderAdapter
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.utils.Constants
import com.example.retromvvm.viewModels.RandomViewModel
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
            header = LoaderAdapter(),
            footer = LoaderAdapter())
    }


}