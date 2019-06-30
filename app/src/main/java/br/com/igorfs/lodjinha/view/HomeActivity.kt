package br.com.igorfs.lodjinha.view

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import br.com.igorfs.lodjinha.R
import br.com.igorfs.lodjinha.adapter.HomeHeaderListAdapter
import br.com.igorfs.lodjinha.service.BannerService
import br.com.igorfs.lodjinha.util.RetrofitFactory
import br.com.igorfs.lodjinha.util.callback
import br.com.igorfs.lodjinha.viewmodel.HomeViewModel
import br.com.igorfs.lodjinha.vo.HomeBannerVo
import kotlinx.android.synthetic.main.activity_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeActivity : AppCompatActivity() {

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        homeViewModel = ViewModelProviders
            .of(this)
            .get(HomeViewModel::class.java)

        homeViewModel.fetchHomeBanner()
        setupView()
    }

    private fun setupView() {
        val adapter = HomeHeaderListAdapter()
        header_list_recyclerview.adapter = adapter

        homeViewModel.getBannerList().observe(this, Observer {
            adapter.loadItems(it?: emptyList())
        })
    }
}
