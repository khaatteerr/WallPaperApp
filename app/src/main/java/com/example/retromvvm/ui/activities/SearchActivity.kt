package com.example.retromvvm.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.lifecycleScope
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.ActivitySearchAcivityBinding
import com.example.retromvvm.model.paging.loadingState.LoadStateAdapter
import com.example.retromvvm.recyclerView.RecyclerViewAdapter
import com.example.retromvvm.viewModels.SearchViewModel
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit

class SearchActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySearchAcivityBinding
    private val viewModel: SearchViewModel by viewModels()
    private val recyclerViewAdapter = RecyclerViewAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchAcivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        initRecyclerView()
        initViewModel()

    }

    private fun initRecyclerView() {
        val layoutManager = GridLayoutManager(this, 3)
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

    private fun handelError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(this, "${it.error}", Toast.LENGTH_LONG).show()
        }

    }


    private fun initViewModel() {


        val observable = Observable.create { emitter ->
            binding.searchEditText.doOnTextChanged { text, _, _, _ ->
                emitter.onNext(text.toString())
            }
        }.debounce(500, TimeUnit.MILLISECONDS)

        observable.subscribe(
            { t ->

                lifecycleScope.launch(Dispatchers.IO) {
                    viewModel.searchFromApi(t).collect {
                        recyclerViewAdapter.submitData(it)
                    }
                }
            }, { e ->
                Toast.makeText(this, "${e.message}", Toast.LENGTH_LONG).show()
            }
        )
    }

}





