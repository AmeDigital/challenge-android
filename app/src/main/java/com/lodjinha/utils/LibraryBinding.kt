package com.lodjinha.utils

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewpager.widget.ViewPager
import com.google.gson.Gson
import com.lodjinha.home.BannerFragment
import com.lodjinha.home.BannerPagerAdapter
import com.lodjinha.home.CategoriesAdapter
import com.lodjinha.home.ProductsAdapter
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Products
import com.lodjinha.repository.utils.Resource
import com.squareup.picasso.Picasso

object LibraryBinding {

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T> showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
        if (resource != null) view.isRefreshing = resource.status == Resource.Status.LOADING
    }

    @BindingAdapter("app:itemsProduct")
    @JvmStatic
    fun managerProductsAdapter(recyclerView: RecyclerView, resource: Resource<Products>?) {
        with(recyclerView.adapter as ProductsAdapter) {
            resource?.data?.let {
                if (it.offset == 0) {
                    updateProducts(it)
                } else {
                    addProducts(it)
                }
            }
        }
    }

    @BindingAdapter("app:itemsCategory")
    @JvmStatic
    fun managerCategoriesAdapter(recyclerView: RecyclerView, resource: Resource<Categories>?) {
        with(recyclerView.adapter as CategoriesAdapter) {
            resource?.data?.let {
                updateCategories(it)
            }
        }
    }

    @BindingAdapter("app:itemsBanner")
    @JvmStatic
    fun managerBannerAdapter(viewPager: ViewPager, resource: Resource<Banners>?) {
        with(viewPager.adapter as BannerPagerAdapter) {
            resource?.data?.let {
                val gson = Gson()
                it.data.forEach { banner ->
                    addFragment(BannerFragment().apply {
                        arguments = Bundle().apply { putString(BannerFragment.BANNERS_KEY, gson.toJson(banner)) }
                    })
                }
            }
        }
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) = Picasso.get().load(url).into(view)

    @BindingAdapter("app:showWhenEmptyListProducts")
    @JvmStatic
    fun showMessageErrorWhenEmptyList(view: View, resource: Resource<Products>?) {
        if (resource != null) {
            view.visibility = if (resource.status != Resource.Status.LOADING
                && resource.data!!.data.isNullOrEmpty()
            ) View.VISIBLE else View.GONE
        }
    }
}
