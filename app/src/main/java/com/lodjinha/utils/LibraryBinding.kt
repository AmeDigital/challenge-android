package com.lodjinha.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.lodjinha.repository.utils.Resource
import com.squareup.picasso.Picasso

object LibraryBinding {

    @BindingAdapter("app:showWhenLoading")
    @JvmStatic
    fun <T> showWhenLoading(view: SwipeRefreshLayout, resource: Resource<T>?) {
        if (resource != null) view.isRefreshing = resource.status == Resource.Status.LOADING
    }

    @BindingAdapter("app:imageUrl")
    @JvmStatic
    fun loadImage(view: ImageView, url: String) =
        Picasso
            .get()
            .load(url)
            .into(view)
}
