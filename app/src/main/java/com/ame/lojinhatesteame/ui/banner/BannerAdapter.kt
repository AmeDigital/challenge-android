package com.ame.lojinhatesteame.ui.banner

import com.ame.lojinhatesteame.data.model.Banner
import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class BannerAdapter(private var banners: List<Banner>) : SliderAdapter() {

    override fun getItemCount(): Int {
        return banners.size
    }

    override fun onBindImageSlide(position: Int, imageSlideViewHolder: ImageSlideViewHolder?) {
        imageSlideViewHolder?.bindImageSlide(banners[position].urlImage)
    }
}