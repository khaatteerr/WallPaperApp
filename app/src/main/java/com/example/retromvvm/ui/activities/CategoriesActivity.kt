package com.example.retromvvm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.R
import com.example.retromvvm.databinding.ActivityCategoriesBinding
import com.example.retromvvm.recyclerView.RecyclerViewAdapter
import com.example.retromvvm.utils.Constants
import com.example.retromvvm.viewModels.MainViewModel
import kotlinx.coroutines.launch

class CategoriesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCategoriesBinding
    private val viewModel: MainViewModel by viewModels()
    private val recyclerViewAdapter = RecyclerViewAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCategoriesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initRecyclerView()
        initViewModel()
        supportActionBar?.setDisplayShowTitleEnabled(false)

    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
        binding.wallCategoriesRecyclerView.layoutManager = layoutManager
        binding.wallCategoriesRecyclerView.adapter = recyclerViewAdapter
    }

    private fun initViewModel() {
        val categoryName = intent.extras?.getString(Constants.CATEGORY)
        lifecycleScope.launch {
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
}