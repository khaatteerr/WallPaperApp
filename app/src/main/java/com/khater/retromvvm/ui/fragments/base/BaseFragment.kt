package com.khater.retromvvm.ui.fragments.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.viewbinding.ViewBinding
import com.khater.retromvvm.recyclerView.RecyclerViewAdapter

import java.lang.IllegalArgumentException

abstract class BaseFragment<VB : ViewBinding  >(
    private val bindingInflater: (inflater: LayoutInflater) -> VB
) : Fragment() {


    private var _binding: VB? = null
    val binding: VB
        get() = _binding as VB




    abstract  var recyclerViewAdapter: RecyclerViewAdapter



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = bindingInflater.invoke(inflater)

        if (_binding == null) {
            throw IllegalArgumentException("binding cannot be null")
        }
        initViewModel()
        recyclerAdapter()
        return binding.root
    }


    abstract fun initViewModel()
    abstract fun recyclerAdapter()


    fun handelError(loadStates: CombinedLoadStates) {
        val errorState = loadStates.source.append as? LoadState.Error
            ?: loadStates.source.prepend as? LoadState.Error

        errorState?.let {
            Toast.makeText(context, "try again later", Toast.LENGTH_LONG).show()
        }

    }


}
