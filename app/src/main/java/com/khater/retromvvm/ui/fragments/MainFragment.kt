package com.khater.retromvvm.ui.fragments

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Lifecycle
import androidx.navigation.Navigation
import com.google.android.gms.ads.AdRequest
import com.google.android.material.tabs.TabLayoutMediator
import com.khater.retromvvm.R
import com.khater.retromvvm.databinding.FragmentTestBinding
import com.khater.retromvvm.ui.fragments.adapter.ViewPagerAdapter
import com.khater.retromvvm.utils.Constants



class MainFragment : Fragment() {
    private lateinit var binding: FragmentTestBinding
    private val fragments =
        listOf(HomeFragment(), PopularFragment(), RandomFragment(), CategoriesFragment())

    private val tabTitles = listOf(Constants.HOME, Constants.POPULAR, Constants.RANDOM, Constants.CATEGORY_NAME)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View  {

        binding = FragmentTestBinding.inflate(layoutInflater,container,false)
        loadAd()
        initViewPager()
        initTabLayout()
        initToolBar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        menuInflater(view)

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
            val pagerAdapter = ViewPagerAdapter(context as FragmentActivity, fragments)
            binding.viewPager.adapter = pagerAdapter
            binding.viewPager.isUserInputEnabled = false
        }


    private fun initToolBar() {
        binding.toolbar.title = "Wallpapers"
        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)
    }

    private fun menuInflater(view:View){
        val menuHost: MenuHost  = requireActivity()

        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.pop_up_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.search -> {
                        val bundle = Bundle()
                        bundle.putString("searchFragment","thisIsSearchFragment")
                        Navigation.findNavController(view).navigate(R.id.action_testFragment_to_searchFragment,bundle)
                        true
                    }
                    else -> false
                }

            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)

    }



}