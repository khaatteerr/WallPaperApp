package com.example.retromvvm.ui.fragments

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentPopularBinding
import com.example.retromvvm.model.paging.LoaderAdapter
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.utils.Constants
import com.example.retromvvm.viewModels.PopularViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class PopularFragment : BaseFragment<FragmentPopularBinding>(
    FragmentPopularBinding::inflate
) {
    private val viewModel:  PopularViewModel by viewModels()

    override fun initViewModel() {
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.popularPage.collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallRecyclerViewPopular.layoutManager = layoutManager
        binding.wallRecyclerViewPopular.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoaderAdapter(),
            footer = LoaderAdapter()
        )
    }

}