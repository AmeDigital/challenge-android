package com.luizzabuscka.alodjinha.components.adapters

import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.luizzabuscka.commons.model.Banner
import org.jetbrains.anko.browse

class BannersAdapter(private val items: List<Banner>) : PagerAdapter() {

    override fun isViewFromObject(view: View, obj: Any): Boolean = view == obj

    override fun getCount(): Int = items.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)

        Glide
            .with(imageView.context)
            .load(items[position].urlImage)
            .centerCrop()
            .into(imageView)

        imageView.setOnClickListener {
            imageView.context.browse(items[position].urlLink)
        }

        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, obj: Any) {
        container.removeView(obj as View)
    }
}