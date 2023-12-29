package com.mealotopia.client.view.activity.HomeActivity

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter


class AdapterFragmentDrawerNav(
    val fragmentManager: FragmentManager,
    val lifecycle: Lifecycle,
): FragmentStateAdapter(fragmentManager, lifecycle) {

    val fragmentList: ArrayList<Fragment> = ArrayList<Fragment>()
    override fun getItemCount(): Int = fragmentList.size

    override fun createFragment(position: Int): Fragment {
        return fragmentList.get(position)
    }
}