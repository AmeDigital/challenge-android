package br.com.igorfs.lodjinha.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.adapter.CategoryListAdapter
import br.com.igorfs.lodjinha.adapter.HomeHeaderListAdapter
import br.com.igorfs.lodjinha.util.CirclePagerIndicatorDecoration
import br.com.igorfs.lodjinha.viewmodel.HomeViewModel
import kotlinx.android.synthetic.main.activity_home.*

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
    }

    private fun setupView() {
        setupBannerRecyclerView()
        setupCategoryRecyclerView()
    }

    private fun setupBannerRecyclerView() {
        val adapter = HomeHeaderListAdapter()

        with(header_list_recyclerview) {
            this.adapter = adapter

            layoutManager = LinearLayoutManager(
                    this@HomeActivity,
                    LinearLayoutManager.HORIZONTAL, false
            )

            addItemDecoration(CirclePagerIndicatorDecoration())
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(header_list_recyclerview)

        homeViewModel.getBannerList().observe(this, Observer {
            adapter.loadItems(it ?: emptyList())
        })
    }

    private fun setupCategoryRecyclerView() {
        val adapter = CategoryListAdapter()

        category_list_recyclerview.adapter = adapter
        category_list_recyclerview.layoutManager = LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false)

        homeViewModel.getCategories().observe(this, Observer {
            adapter.loadItems(it ?: emptyList())
        })
    }
}
