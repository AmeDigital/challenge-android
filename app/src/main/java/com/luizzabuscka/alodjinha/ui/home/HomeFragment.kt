package com.luizzabuscka.alodjinha.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.ui.home.adapters.BannersAdapter
import com.luizzabuscka.commons.mock.mockBanners
import com.luizzabuscka.commons.mock.mockCategories
import com.luizzabuscka.commons.models.Banner
import com.luizzabuscka.commons.models.Category
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.luizzabuscka.alodjinha.ui.home.adapters.CategoriesAdapter


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configBanners(mockBanners())
        configCategories(mockCategories())
    }

    private fun configBanners(items: List<Banner>) {
        vpBanners.adapter = BannersAdapter(items)

        tabLayoutBanners.setupWithViewPager(vpBanners, true)
        tabLayoutBanners.bringToFront()
    }

    private fun configCategories(items: List<Category>) {
        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
        rvCategories.layoutManager = layoutManager
        rvCategories.adapter = CategoriesAdapter(items)
    }


}
