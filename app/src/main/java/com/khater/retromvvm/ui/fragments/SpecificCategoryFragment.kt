package com.khater.retromvvm.ui.fragments


 import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.khater.retromvvm.databinding.FragmentSpecificCategoryBinding
import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.CategoriesViewModel
import com.khater.retromvvm.viewModels.CategoriesViewModelFactory
import kotlinx.coroutines.launch


class SpecificCategoryFragment : BaseFragment<FragmentSpecificCategoryBinding>(
    FragmentSpecificCategoryBinding::inflate
) {

    private lateinit var viewModel: CategoriesViewModel
    private val args: SpecificCategoryFragmentArgs by  navArgs()


    override fun initViewModel() {




        viewModel =
            ViewModelProvider(this, CategoriesViewModelFactory(args.categoryName))[CategoriesViewModel::class.java]

        viewModel.data.observe(this@SpecificCategoryFragment) {
            lifecycleScope.launch {
                recyclerViewAdapter.submitData(it)
            }
        }

    }

    override fun recyclerAdapter() {

        val layoutManager = GridLayoutManager(context, 3)
        binding.wallCategoriesRecyclerView.layoutManager = layoutManager
        binding.wallCategoriesRecyclerView.adapter =
            recyclerViewAdapter.withLoadStateHeaderAndFooter(
                header = LoadStateAdapter { recyclerViewAdapter.retry() },
                footer = LoadStateAdapter { recyclerViewAdapter.retry() }
            )
        recyclerViewAdapter.addLoadStateListener { loadState ->
            binding.wallCategoriesRecyclerView.isVisible =
                loadState.source.refresh is LoadState.NotLoading
            binding.CategoryProgressBar.isVisible = loadState.source.refresh is LoadState.Loading
            binding.CategoryButtonRetry.isVisible =
                loadState.source.refresh is LoadState.Error
            handelError(loadState)
            loadAd()
        }
        binding.CategoryButtonRetry.setOnClickListener {
            recyclerViewAdapter.retry()
        }
        categoryName()
    }

    private fun loadAd() {
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }

    override var recyclerViewAdapter: RecyclerViewAdapter = RecyclerViewAdapter(
        Constants.NavigationIntent.FromCategoryToDownload
    )

    private fun categoryName() {
        binding.categoryName.text = args.categoryName
    }

}