package com.example.retromvvm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.R
import com.example.retromvvm.databinding.ActivityCategoriesBinding
import com.example.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.example.retromvvm.recyclerView.RecyclerViewAdapter
import com.example.retromvvm.utils.Constants
import com.example.retromvvm.viewModels.CategoriesViewModel
import com.google.android.gms.ads.AdRequest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private val viewModel: CategoriesViewModel by viewModels()
    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
        supportActionBar?.title = intent.extras?.getString(Constants.CATEGORY)
        loadAd()
    }


    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
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
            binding.CategoryRandomButtonRetry.isVisible =
                loadState.source.refresh is LoadState.Error
            handelError(loadState)

        }
    }

    private fun handelError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(this, "${it.error}", Toast.LENGTH_LONG).show()
        }

    }

    private fun initViewModel() {
        val categoryName = intent.extras?.getString(Constants.CATEGORY)
        lifecycleScope.launch(Dispatchers.IO) {
            viewModel.loadCategoryToRandom(categoryName.toString()).collect {
                recyclerViewAdapter.submitData(it)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_back -> {
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loadAd(){
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
    }
}