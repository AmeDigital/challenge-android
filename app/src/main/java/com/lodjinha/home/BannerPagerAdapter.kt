package com.lodjinha.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class BannerPagerAdapter(manager: FragmentManager, private val fragments: List<Fragment>) :
    FragmentStatePagerAdapter(manager) {

    override fun getCount(): Int = fragments.size

    override fun getItem(position: Int): Fragment = fragments[position]
}
