package br.com.igorfs.lodjinha.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.PagerSnapHelper
import br.com.igorfs.lodjinha.R
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

    private fun setupView() {
        val adapter = HomeHeaderListAdapter()

        with(header_list_recyclerview){
            this.adapter = adapter

            layoutManager = LinearLayoutManager(this@HomeActivity,
                LinearLayoutManager.HORIZONTAL, false)

            addItemDecoration(CirclePagerIndicatorDecoration())
        }

        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(header_list_recyclerview)

        homeViewModel.getBannerList().observe(this, Observer {
            adapter.loadItems(it?: emptyList())
        })
    }

    private fun setupCall() {
        homeViewModel.fetchBannerList()
    }
}
