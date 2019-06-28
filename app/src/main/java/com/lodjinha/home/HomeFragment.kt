package com.lodjinha.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.lodjinha.base.BaseFragment
import com.lodjinha.base.BaseViewModel
import com.lodjinha.databinding.FragmentHomeBinding
import com.lodjinha.model.Banners
import com.lodjinha.repository.utils.Resource
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment() {

    private val viewModel: HomeViewModel by viewModel()
    private lateinit var binding: FragmentHomeBinding

    override fun getViewModel(): BaseViewModel = viewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        initViews(binding)

        binding.viewModel!!.banners.observe(this, Observer<Resource<Banners>> {
            if (it.status == Resource.Status.SUCCESS) {
                configBanner(it.data, binding)
            }
        })

        return binding.root
    }

    private fun configBanner(banners: Banners?, binding: FragmentHomeBinding) {
        val bannersFragments = mutableListOf<Fragment>()
        val gson = Gson()
        banners?.data?.forEach { banner ->
            bannersFragments.add(BannerFragment().apply {
                arguments = Bundle().apply {
                    putString(BannerFragment.BANNERS_KEY, gson.toJson(banner))
                }
            })
        }

        binding.banner.adapter = BannerPagerAdapter(activity!!.supportFragmentManager, bannersFragments)
    }

    private fun initViews(binding: FragmentHomeBinding) {

    }
}
