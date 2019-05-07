package br.com.sf.lojinha.ui.home

import ss.com.bannerslider.adapters.SliderAdapter
import ss.com.bannerslider.viewholder.ImageSlideViewHolder

class HomeSliderAdapter(private val slides: List<String>) : SliderAdapter() {

    override fun getItemCount(): Int {
        return slides.size
    }

    override fun onBindImageSlide(position: Int, viewHolder: ImageSlideViewHolder) {
        viewHolder.bindImageSlide(slides.get(position))
    }
}