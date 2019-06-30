package com.example.lodjinha.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.domain.models.Banner
import com.example.lodjinha.modelVO.map

class BannerAdapter(fragmentManager: FragmentManager, private val banner: List<Banner>) :
    FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return BannerFragment.newInstance(banner[position].map())
    }

    override fun getCount(): Int {
        return banner.size
    }
}