package com.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lodjinha.base.BaseFragment
import com.lodjinha.base.BaseViewModel
import com.lodjinha.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initViews(binding)

        return binding.root
    }

    private fun initViews(binding: FragmentHomeBinding) = with(binding) {
        initBannerView(this)
        initCategoriesListView(this)
        initTopSellingListView(this)
    }

    private fun initBannerView(binding: FragmentHomeBinding) = with(binding.banner) {
        adapter = BannerPagerAdapter(activity!!.supportFragmentManager)
        binding.bannerIndicator.setupWithViewPager(this, true)
    }

    private fun initCategoriesListView(binding: FragmentHomeBinding) = with(binding.recyclerViewCategories) {
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = CategoriesAdapter()
    }

    private fun initTopSellingListView(binding: FragmentHomeBinding) = with(binding.recyclerViewTopSelling) {
        setHasFixedSize(true)
        adapter = ProductsAdapter()
        val linearLayoutManager = LinearLayoutManager(context)
        addItemDecoration(DividerItemDecoration(context, linearLayoutManager.orientation))
        layoutManager = linearLayoutManager
    }
}
