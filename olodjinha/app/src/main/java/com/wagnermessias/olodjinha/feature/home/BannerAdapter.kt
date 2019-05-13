package com.wagnermessias.olodjinha.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.wagnermessias.olodjinha.R
import com.wagnermessias.olodjinha.core.model.Banner


class BannerAdapter(private val banners: List<Banner>) : PagerAdapter() {

    override fun isViewFromObject(view: View, `object`: Any) = view == `object`

    override fun getCount() = banners.size

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val view: View = LayoutInflater.from(container.context).inflate(R.layout.banner_item, container, false)

        (view.findViewById(R.id.image_banner) as ImageView).apply {
            Glide.with(container.context)
                .load(banners[position].linkUrl)
                .centerCrop()
                .into(this)
            setOnClickListener {
                //Toast.makeText(context, "Você clicou na imagem, Toast.LENGTH_LONG).show()
            }
        }
        container.addView(view)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}