package com.ame.lojinhatesteame.util

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.widget.ImageView
import com.squareup.picasso.Picasso

class BindingAdapters {
    companion object {
        @BindingAdapter("items")
        @JvmStatic
        fun setItems(recyclerView: RecyclerView, items: MutableList<Any>) {

            recyclerView.adapter.let {
                if (it is AdapterItemsContract) {
                    it.replaceItems(items)
                }
            }
        }

        @BindingAdapter( "picassoLoad")
        @JvmStatic
        fun loadRemoteImage(iv: ImageView, url: String ){
            Picasso.get().load(url).into(iv)
        }
    }
}