package com.example.retromvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
 import com.example.retromvvm.databinding.FragmentCategoriesBinding
import com.example.retromvvm.recyclerView.CategoriesAdapter
import com.example.retromvvm.utils.ApiListCategory
import com.google.android.gms.ads.AdRequest


class CategoriesFragment : Fragment() {
    private lateinit var binding: FragmentCategoriesBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)
        recyclerAdapter()
         val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
        return binding.root
    }

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        val recyclerViewAdapter = CategoriesAdapter(ApiListCategory.list)
        binding.categoriesRecyclerView.layoutManager = layoutManager
        binding.categoriesRecyclerView.adapter = recyclerViewAdapter
    }


}