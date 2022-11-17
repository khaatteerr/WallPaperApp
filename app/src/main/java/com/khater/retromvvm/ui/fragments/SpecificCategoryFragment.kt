package com.khater.retromvvm.ui.fragments


 import android.view.View
 import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
 import androidx.navigation.Navigation
 import androidx.navigation.fragment.navArgs
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.gms.ads.AdRequest
import com.khater.retromvvm.databinding.FragmentSpecificCategoryBinding
 import com.khater.retromvvm.model.domain.Data
 import com.khater.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter
 import com.khater.retromvvm.recyclerView.WallInteractionListener
 import com.khater.retromvvm.ui.fragments.base.BaseFragment
import com.khater.retromvvm.utils.Constants
import com.khater.retromvvm.viewModels.CategoriesViewModel
import com.khater.retromvvm.viewModels.CategoriesViewModelFactory
import kotlinx.coroutines.launch


class SpecificCategoryFragment : BaseFragment<FragmentSpecificCategoryBinding>(
    FragmentSpecificCategoryBinding::inflate
),WallInteractionListener {

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
     this)

    private fun categoryName() {
        binding.categoryName.text = args.categoryName
    }

    override fun onClickItem(data: Data, view: View) {
        val imageData = arrayOf(data.fullImageUrl.toString(), data.blurHash.toString())
        Navigation.findNavController(view)
            .navigate(
                SpecificCategoryFragmentDirections.actionSpecificCategoryFragmentToDownloadFragment(
                    imageData
                )
            )
    }

}