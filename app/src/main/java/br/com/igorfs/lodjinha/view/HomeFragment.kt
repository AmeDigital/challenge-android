package br.com.igorfs.lodjinha.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.adapter.BannerListAdapter
import br.com.igorfs.lodjinha.adapter.CategoryListAdapter
import br.com.igorfs.lodjinha.adapter.ProductsAdapter
import br.com.igorfs.lodjinha.util.CirclePagerIndicatorDecoration
import br.com.igorfs.lodjinha.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.fragment_home.header_list_recyclerview
import kotlinx.android.synthetic.main.fragment_home.home_category_recyclerview
import kotlinx.android.synthetic.main.fragment_home.top_sellers_list_recyclerview

class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders
            .of(this)
            .get(HomeViewModel::class.java)

        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupView()
        setupCall()
    }

    private fun setupCall() {
        homeViewModel.fetchBannerList()
        homeViewModel.fetchCategoryList()
        homeViewModel.fetchTopSellersData()
    }

    private fun setupView() {
        setupBannerRecyclerView()
        setupCategoryRecyclerView()
        setupTopSellersRecyclerView()
    }

    private fun setupBannerRecyclerView() {
        val bannerAdapter = BannerListAdapter()

        with(header_list_recyclerview) {
            this.adapter = bannerAdapter

            layoutManager = LinearLayoutManager(
                    requireContext(),
                    LinearLayoutManager.HORIZONTAL, false
            )

            addItemDecoration(CirclePagerIndicatorDecoration())
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(header_list_recyclerview)

        homeViewModel.getBannerList().observe(this, Observer {
            bannerAdapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupCategoryRecyclerView() {
        val categoryAdapter = CategoryListAdapter()

        home_category_recyclerview.adapter = categoryAdapter
        home_category_recyclerview.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.getCategories().observe(this, Observer {
            categoryAdapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupTopSellersRecyclerView() {
        val topSellersAdapter = ProductsAdapter()

        top_sellers_list_recyclerview.adapter = topSellersAdapter
        top_sellers_list_recyclerview.layoutManager = LinearLayoutManager(requireContext())
        top_sellers_list_recyclerview.isNestedScrollingEnabled = false

        homeViewModel.getTopSellers().observe(this, Observer {
            topSellersAdapter.loadItems(it ?: emptyList())
        })
    }
}
