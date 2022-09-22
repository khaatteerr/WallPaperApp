package com.example.retromvvm.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.retromvvm.R
import com.example.retromvvm.databinding.ActivityMainBinding
import com.example.retromvvm.ui.fragments.CategoriesFragment
import com.example.retromvvm.ui.fragments.PopularFragment
import com.example.retromvvm.ui.fragments.HomeFragment
import com.example.retromvvm.ui.fragments.RandomFragment
import com.example.retromvvm.ui.fragments.adapter.ViewPagerAdapter
import com.example.retromvvm.utils.Constants
import com.google.android.gms.ads.AdRequest
import com.google.android.material.tabs.TabLayoutMediator



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
     private val fragments =
        listOf(HomeFragment(), PopularFragment(), RandomFragment(), CategoriesFragment())

    private val tabTitles = listOf(Constants.HOME, Constants.POPULAR, Constants.RANDOM, Constants.CATEGORY_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initToolBar()
        initViewPager()
        initTabLayout()
        loadAd()
    }

    private fun loadAd(){
        val adRequest = AdRequest.Builder().build()
        binding.adView.loadAd(adRequest)
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


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.pop_up_menu, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.search -> {
                startActivity(Intent(this,SearchActivity::class.java))
             }
         }
        return super.onOptionsItemSelected(item)
    }


}