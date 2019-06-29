package com.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.lodjinha.base.BaseFragment
import com.lodjinha.base.BaseViewModel
import com.lodjinha.databinding.FragmentHomeBinding
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Products
import com.lodjinha.repository.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var categoriesListAdapter: CategoriesListAdapter
    private lateinit var topSellingListAdapter: TopSellingListAdapter
    private lateinit var bannerPagerAdapter: BannerPagerAdapter

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initViews(binding)

        binding.viewModel!!.banners.observe(this, Observer<Resource<Banners>> {
            if (it.status == Resource.Status.SUCCESS) {
                configBanner(it.data)
            }
        })

        binding.viewModel!!.categories.observe(this, Observer<Resource<Categories>> { resource ->
            if (resource.status == Resource.Status.SUCCESS) {
                resource.data?.let { categoriesListAdapter.updateCategories(it) }
            }
        })

        binding.viewModel!!.topSelling.observe(this, Observer<Resource<Products>> { resource ->
            if (resource.status == Resource.Status.SUCCESS) {
                resource.data?.let { topSellingListAdapter.updateProducts(it) }
            }
        })

        return binding.root
    }

    private fun initViews(binding: FragmentHomeBinding) = with(binding) {
        initBannerView(this)
        initCategoriesListView(this)
        initTopSellingListView(this)
    }

    private fun initBannerView(binding: FragmentHomeBinding) = with(binding.banner) {
        bannerPagerAdapter = BannerPagerAdapter(activity!!.supportFragmentManager)
        adapter = bannerPagerAdapter
    }

    private fun initCategoriesListView(binding: FragmentHomeBinding) = with(binding.recyclerViewCategories) {
        categoriesListAdapter = CategoriesListAdapter()
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        adapter = categoriesListAdapter
    }

    private fun initTopSellingListView(binding: FragmentHomeBinding) = with(binding.recyclerViewTopSelling) {
        topSellingListAdapter = TopSellingListAdapter()
        setHasFixedSize(true)
        layoutManager = LinearLayoutManager(context)
        adapter = topSellingListAdapter
    }

    private fun configBanner(banners: Banners?) {
        val gson = Gson()
        banners?.data?.forEach { banner ->
            bannerPagerAdapter.addFragment(BannerFragment().apply {
                arguments = Bundle().apply {
                    putString(BannerFragment.BANNERS_KEY, gson.toJson(banner))
                }
            })
        }
    }
}
