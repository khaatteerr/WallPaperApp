package com.khater.retromvvm.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
 import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.ActivityMainBinding

import com.google.android.gms.ads.AdRequest
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.khater.retromvvm.ui.fragments.CategoriesFragment
import com.khater.retromvvm.ui.fragments.HomeFragment
import com.khater.retromvvm.ui.fragments.PopularFragment
import com.khater.retromvvm.ui.fragments.RandomFragment
import com.khater.retromvvm.ui.fragments.adapter.ViewPagerAdapter
import com.khater.retromvvm.utils.Constants


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
     private val fragments =
        listOf(HomeFragment(), PopularFragment(), RandomFragment(), CategoriesFragment())

    private val tabTitles = listOf(Constants.HOME, Constants.POPULAR, Constants.RANDOM, Constants.CATEGORY_NAME)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        initToolBar()
//        initViewPager()
//        initTabLayout()
//        loadAd()
    }

//    private fun loadAd(){
//        val adRequest = AdRequest.Builder().build()
//        binding.adView.loadAd(adRequest)
//    }



//    private fun initTabLayout() {
//         TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
//            tab.text = tabTitles[position]
//        }.attach()
//
//    }

//    private fun initViewPager() {
//        val pagerAdapter = ViewPagerAdapter(this, fragments)
//        binding.viewPager.adapter = pagerAdapter
//        binding.viewPager.isUserInputEnabled = false
//    }
//
//    private fun initToolBar() {
//        binding.toolbar.title = "Wallpapers"
//        setSupportActionBar(binding.toolbar)
//    }


//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//
//        menuInflater.inflate(R.menu.pop_up_menu, menu)
//        return true
//    }
//
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when (item.itemId) {
//            R.id.search -> {
//
//           //     startActivity(Intent(this,SearchActivity::class.java))
//             }
//         }
//        return super.onOptionsItemSelected(item)
//    }


}