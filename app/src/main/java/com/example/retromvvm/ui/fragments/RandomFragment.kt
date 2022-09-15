package com.example.retromvvm.ui.fragments

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentRandomBinding
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.utils.Constants
import kotlinx.coroutines.launch

class RandomFragment : BaseFragment<FragmentRandomBinding>(
    FragmentRandomBinding::inflate
) {


    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.loadDataParameter(Constants.RANDOM).collect {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.randomRecyclerView.layoutManager = layoutManager
        binding.randomRecyclerView.adapter = recyclerViewAdapter
    }


}