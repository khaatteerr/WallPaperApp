package com.example.retromvvm.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.example.retromvvm.recyclerView.RecyclerViewAdapter
import com.example.retromvvm.viewModels.MainViewModel
import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding>(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {


    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB

    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    val viewModel: MainViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        if (_binding == null) {
            throw IllegalArgumentException("binding cannot be null")
        }
        recyclerViewAdapter = RecyclerViewAdapter()
        initViewModel()
        recyclerAdapter()
        return binding.root
    }

    abstract fun initViewModel()
    abstract fun recyclerAdapter()

}