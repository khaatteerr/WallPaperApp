package com.example.retromvvm.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.retromvvm.databinding.ActivityMainBinding
import com.example.retromvvm.ui.fragments.CategoriesFragment
import com.example.retromvvm.ui.fragments.PopularFragment
import com.example.retromvvm.ui.fragments.HomeFragment
import com.example.retromvvm.ui.fragments.RandomFragment
import com.example.retromvvm.ui.fragments.adapter.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val fragments =
        listOf(HomeFragment(), PopularFragment(), RandomFragment(), CategoriesFragment())

    private val tabTitles = listOf("Home", "Popular", "Random", "Categories")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()
        initViewPager()
        initTabLayout()


    }

    private fun initTabLayout() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()
    }

    private fun initViewPager() {
        val pagerAdapter = ViewPagerAdapter(this, fragments)
        binding.viewPager.adapter = pagerAdapter
        binding.viewPager.isUserInputEnabled = false
    }

    private fun initToolBar() {
        binding.toolbar.title = "Wallpapers"
        setSupportActionBar(binding.toolbar)
    }

}