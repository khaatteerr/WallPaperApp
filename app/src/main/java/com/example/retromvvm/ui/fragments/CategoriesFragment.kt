package com.example.retromvvm.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.retromvvm.databinding.FragmentCategoriesBinding
import com.example.retromvvm.model.domain.Category
import com.example.retromvvm.recyclerView.CategoriesAdapter

class CategoriesFragment:  Fragment() {
    private lateinit var binding: FragmentCategoriesBinding
    private val list = listOf(
        Category("Gaming","https://images.unsplash.com/photo-1616440537338-1d04df3987f7?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw5N3x8R2FtaW5nfGVufDB8MXx8fDE2MzM0Njg0Mjc&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Food","https://images.unsplash.com/photo-1532635215-25385bb7c5d1?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwzMDAzfHxGb29kfGVufDB8MXx8fDE2MzE4ODE4ODA&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Art","https://images.unsplash.com/photo-1596644573908-7254b2e81456?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwyNTkxfHxBcnR8ZW58MHwxfHx8MTYzMjA2MzA2OA&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Cars","https://images.pexels.com/photos/9584373/pexels-photo-9584373.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"),
        Category("Black","https://images.unsplash.com/photo-1602748152445-e24de6ea295d?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwyNzgwfHxCbGFja3xlbnwwfDF8fHwxNjMxODgxMjU0&ixlib=rb-1.2.1&q=80&w=400"),
        Category("City","https://images.unsplash.com/photo-1595865766314-f402c265cabb?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwzODM4fHxDaXR5fGVufDB8MXx8fDE2MzE4ODE0NjQ&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Animals","https://images.unsplash.com/photo-1619065402614-0e5569e78684?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwxNDEyfHxBbmltYWxzfGVufDB8MXx8fDE2MzE4ODExOTE&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Flowers","https://images.pexels.com/photos/5566973/pexels-photo-5566973.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"),
        Category("Fantasy","https://images.unsplash.com/photo-1572883454114-1cf0031ede2a?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwxNjE5fHxGYW50YXN5fGVufDB8MXx8fDE2MzE4ODE1MDk&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Love","https://images.pexels.com/photos/916361/pexels-photo-916361.jpeg?auto=compress&cs=tinysrgb&h=650&w=940"),
        Category("3D","https://images.unsplash.com/photo-1618151864004-0992c44d411f?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw1MjV8fDNEfGVufDB8MXx8fDE2MzE4ODExNTg&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Abstract","https://images.pexels.com/photos/921289/pexels-photo-921289.png?auto=compress&cs=tinysrgb&h=650&w=940"),
        Category("Holidays","https://images.unsplash.com/photo-1541953747-6bead12436c3?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHwxMzV8fEhvbGlkYXlzfGVufDB8MXx8fDE2MzE2NDExMjY&ixlib=rb-1.2.1&q=80&w=400"),
        Category("Minimalism","https://images.unsplash.com/photo-1555587441-d6327df2b932?crop=entropy&cs=tinysrgb&fit=max&fm=jpg&ixid=MnwyNTUzMjl8MHwxfHNlYXJjaHw1MzV8fE1pbmltYWxpc218ZW58MHwxfHx8MTYzMTg4MjAyNA&ixlib=rb-1.2.1&q=80&w=400")
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        binding = FragmentCategoriesBinding.inflate(inflater,container,false)
        recyclerAdapter()
        return binding.root
    }

    private fun recyclerAdapter() {
        val layoutManager = GridLayoutManager(context, 2)
        val recyclerViewAdapter = CategoriesAdapter(list)
        binding.categoriesRecyclerView.layoutManager = layoutManager
        binding.categoriesRecyclerView.adapter = recyclerViewAdapter
    }
}