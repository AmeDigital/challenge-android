package br.com.igorfs.lodjinha.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.adapter.BannerListAdapter
import br.com.igorfs.lodjinha.adapter.CategoryListAdapter
import br.com.igorfs.lodjinha.adapter.TopSellersAdapter
import br.com.igorfs.lodjinha.util.CirclePagerIndicatorDecoration
import br.com.igorfs.lodjinha.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.category_list_recyclerview
import kotlinx.android.synthetic.main.activity_home.header_list_recyclerview
import kotlinx.android.synthetic.main.activity_home.top_sellers_list_recyclerview

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProviders
            .of(this)
            .get(HomeViewModel::class.java)

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
                    this@HomeActivity,
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

        category_list_recyclerview.adapter = categoryAdapter
        category_list_recyclerview.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.getCategories().observe(this, Observer {
            categoryAdapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupTopSellersRecyclerView() {
        val topSellersAdapter = TopSellersAdapter()

        top_sellers_list_recyclerview.adapter = topSellersAdapter
        top_sellers_list_recyclerview.layoutManager = LinearLayoutManager(this)
        top_sellers_list_recyclerview.isNestedScrollingEnabled = false

        homeViewModel.getTopSellers().observe(this, Observer {
            topSellersAdapter.loadItems(it ?: emptyList())
        })
    }
}
