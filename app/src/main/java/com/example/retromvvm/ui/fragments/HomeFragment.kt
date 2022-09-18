package com.example.retromvvm.ui.fragments

import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentHomeBinding
import com.example.retromvvm.ui.fragments.base.BaseFragment
import com.example.retromvvm.utils.Constants
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<FragmentHomeBinding>(
    FragmentHomeBinding::inflate
) {



    override fun initViewModel() {
        lifecycleScope.launch {
            viewModel.loadDataParameter(Constants.HOME).collect {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.wallRecyclerView.layoutManager = layoutManager
        binding.wallRecyclerView.adapter = recyclerViewAdapter
    }

}
