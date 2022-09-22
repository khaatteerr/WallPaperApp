package com.khater.retromvvm.model.paging.loadingState

import android.view.ViewGroup
import androidx.paging.LoadState

class LoadStateAdapter(
    private val retry: () -> Unit
) :androidx.paging.LoadStateAdapter<LoadStateViewHolder>(){
    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        return LoadStateViewHolder.create(parent, retry)
    }
}