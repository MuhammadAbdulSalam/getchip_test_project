package com.salam.getchip.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.salam.getchip.R
import com.salam.getchip.adapters.DOGS_LIST_PAGE_INDEX
import com.salam.getchip.adapters.DOGS_SAVED_PAGE_INDEX
import com.salam.getchip.adapters.DogsPagerAdapter
import com.salam.getchip.databinding.FragmentDogViewPagerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogViewPagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentDogViewPagerBinding.inflate(inflater, container, false)
        val tabLayout = binding.tabs
        val viewPager = binding.viewPager

        viewPager.adapter = DogsPagerAdapter(this)

        // Set the icon and text for each tab
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setIcon(getTabIcon(position))
            tab.text = getTabTitle(position)
        }.attach()

        (activity as AppCompatActivity).setSupportActionBar(binding.toolbar)

        return binding.root
    }

    private fun getTabIcon(position: Int): Int {
        return when (position) {
            DOGS_LIST_PAGE_INDEX -> R.drawable.dog_list_tab_selector
            DOGS_SAVED_PAGE_INDEX -> R.drawable.dog_saved_tab_selector
            else -> throw IndexOutOfBoundsException()
        }
    }

    private fun getTabTitle(position: Int): String? {
        return when (position) {
            DOGS_LIST_PAGE_INDEX -> "Dogs List"
            DOGS_SAVED_PAGE_INDEX -> "Saved List"
            else -> null
        }
    }

}