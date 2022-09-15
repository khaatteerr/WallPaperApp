package com.example.retromvvm.ui.fragments

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentPopularBinding
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.utils.Constants
import kotlinx.coroutines.launch


class PopularFragment : BaseFragment<FragmentPopularBinding>(
    FragmentPopularBinding::inflate
) {


    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.loadDataParameter(Constants.POPULAR).collect {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallRecyclerViewPopular.layoutManager = layoutManager
        binding.wallRecyclerViewPopular.adapter = recyclerViewAdapter
    }

}