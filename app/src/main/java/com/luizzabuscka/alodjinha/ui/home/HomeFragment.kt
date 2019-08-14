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
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.viewmodel.HomeViewModel
import org.jetbrains.anko.alert
import org.jetbrains.anko.indeterminateProgressDialog
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private val progressDialog by lazy {
        requireActivity().indeterminateProgressDialog(
            getString(R.string.dialog_please_wait),
            getString(R.string.dialog_loading)
        )
    }

    private val homeViewModel: HomeViewModel by viewModel()

    private var bannersResponded = false
    private var categoriesResponded = false
    private var bestSellersResponded = false

    private var errorLoading = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        progressDialog.setCancelable(false)
        progressDialog.show()

        loadData()
    }

    private fun loadData() {
        bannersResponded = false
        categoriesResponded = false
        bestSellersResponded = false
        errorLoading = false

        getBanners()
        getCategories()
        getBestSellers()
    }

    private fun getBanners() {
        homeViewModel.getBanners().observe(this, Observer<List<Banner>> { banners ->
            bannersResponded = true
            banners?.let {
                configBanners(it)
            } ?: run {
                errorLoading = true
            }
            verifyHideLoading()
        })
    }

    private fun getCategories() {
        homeViewModel.getCategories().observe(this, Observer<List<Category>> { categories ->
            categoriesResponded = true
            categories?.let {
                configCategories(it)
            } ?: run {
                errorLoading = true
            }
            verifyHideLoading()
        })
    }

    private fun getBestSellers() {
        homeViewModel.getBestSellers().observe(this, Observer<List<Product>> { products ->
            bestSellersResponded = true
            products?.let {
                configBestSellers(it)
            } ?: run {
                errorLoading = true
            }
            verifyHideLoading()
        })
    }

    private fun verifyHideLoading() {
        if (bannersResponded && categoriesResponded && bestSellersResponded) {
            if (errorLoading) {
                requireActivity().alert(getString(R.string.dialog_fail_load_data)) {
                    this.isCancelable = false
                    positiveButton(getString(R.string.dialog_button_try_again)) {
                        loadData()
                    }
                }.show()
            }

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
