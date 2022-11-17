package com.khater.retromvvm.ui.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.khater.retromvvm.databinding.FragmentSearchBinding
import com.khater.retromvvm.model.domain.Data
import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.recyclerView.WallInteractionListener
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.SearchViewModel
import kotlinx.coroutines.launch

class SearchFragment : BaseFragment<FragmentSearchBinding>(
    FragmentSearchBinding::inflate

), WallInteractionListener {

    val viewModel: SearchViewModel by viewModels()

    @SuppressLint("SuspiciousIndentation")
    override fun initViewModel() {


        binding.searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                viewModel.searchFromApi(binding.searchEditText.text.toString())

                val imm =
                    context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view?.windowToken, 0)

            }
            true
        }

        viewModel.data.observe(requireActivity()) {
            lifecycleScope.launch {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 3)
        binding.searchRecyclerView.layoutManager = layoutManager
        binding.searchRecyclerView.adapter = recyclerViewAdapter.withLoadStateHeaderAndFooter(
            header = LoadStateAdapter { recyclerViewAdapter.retry() },
            footer = LoadStateAdapter { recyclerViewAdapter.retry() }
        )
        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.searchRecyclerView.isVisible = loadState.source.refresh is LoadState.NotLoading
            binding.searchProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            handelError(loadState)
        }
    }

    override var recyclerViewAdapter: RecyclerViewAdapter =
        RecyclerViewAdapter( this)


    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(
                SearchFragmentDirections.actionSearchFragmentToDownloadFragment(
                    imageData
                )
            )
    }


}