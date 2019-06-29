package com.lodjinha.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BannerPagerAdapter(manager: FragmentManager) :
    FragmentStatePagerAdapter(manager) {

    private val fragments = mutableListOf<Fragment>()

    fun addFragment(fragment: Fragment) {
        fragments.add(fragment)
        notifyDataSetChanged()
    }

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
}
