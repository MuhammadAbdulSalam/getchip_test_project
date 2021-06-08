

package com.salam.getchip.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.salam.getchip.fragments.DogListFragment
import com.salam.getchip.fragments.DogsSavedFragment

const val DOGS_LIST_PAGE_INDEX = 0
const val DOGS_SAVED_PAGE_INDEX = 1

class DogsPagerAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    /**
     * Mapping of the ViewPager page indexes to their respective Fragments
     */
    private val tabFragmentsCreators: Map<Int, () -> Fragment> = mapOf(
       DOGS_LIST_PAGE_INDEX to {DogListFragment()},
        DOGS_SAVED_PAGE_INDEX to {DogsSavedFragment()}
    )

    override fun getItemCount() = tabFragmentsCreators.size

    override fun createFragment(position: Int): Fragment {
        return tabFragmentsCreators[position]?.invoke() ?: throw IndexOutOfBoundsException()
    }
}
