package com.luizzabuscka.alodjinha.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.luizzabuscka.alodjinha.R
import com.luizzabuscka.alodjinha.components.adapters.BannersAdapter
import com.luizzabuscka.commons.model.Banner
import com.luizzabuscka.commons.model.Category
import kotlinx.android.synthetic.main.fragment_home.*
import com.luizzabuscka.alodjinha.components.adapters.CategoriesAdapter
import com.luizzabuscka.alodjinha.components.adapters.ProductsAdapter
import com.luizzabuscka.commons.mock.mockProductsBestSellers
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.viewmodel.HomeViewModel
import org.jetbrains.anko.indeterminateProgressDialog


class HomeFragment : Fragment() {

    private val progressDialog by lazy {
        requireActivity().indeterminateProgressDialog(
            getString(R.string.dialog_please_wait),
            getString(R.string.dialog_loading)
        )
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProviders.of(requireActivity()).get(HomeViewModel::class.java)
    }

    private var bannersResponded = false
    private var categoriesResponded = false
    private var bestSellersResponded = true


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog.show()

        getBanners()
        getCategories()
        getBestSellers()
    }

    private fun getBanners() {
        viewModel.getBanners().observe(this, Observer<List<Banner>> { banners ->
            bannersResponded = true
            verifyHideLoading()
            banners?.let {
                configBanners(it)
            } ?: run {
                //error loading banners
            }
        })
    }

    private fun getCategories() {
        viewModel.getCategories().observe(this, Observer<List<Category>> { categories ->
            categoriesResponded = true
            verifyHideLoading()
            categories?.let {
                configCategories(it)
            } ?: run {
                //error loading categories
            }
        })
    }

    private fun getBestSellers() {
        viewModel.getBestSellers().observe(this, Observer<List<Product>> { products ->
            bestSellersResponded = true
            verifyHideLoading()
            products?.let {
                configBestSellers(it)
            } ?: run {
                //error loading best sellers
            }
        })
    }

    private fun verifyHideLoading() {
        if (bannersResponded && categoriesResponded && bestSellersResponded) {
            progressDialog.hide()
        }
    }

    private fun configBanners(items: List<Banner>) {
        vpBanners.adapter = BannersAdapter(items)
        tabLayoutBanners.setupWithViewPager(vpBanners, true)
        tabLayoutBanners.bringToFront()
    }

    private fun configCategories(items: List<Category>) {
        rvCategories.adapter = CategoriesAdapter(items)
    }

    private fun configBestSellers(items: List<Product>) {
        rvBestSellers.adapter = ProductsAdapter(items)
    }
}
