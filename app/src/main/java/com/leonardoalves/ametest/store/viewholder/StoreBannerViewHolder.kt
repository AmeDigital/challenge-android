package com.leonardoalves.ametest.store.viewholder

import android.os.Handler
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.leonardoalves.ametest.R
import com.leonardoalves.ametest.custom.PageIndicator
import com.leonardoalves.ametest.custom.ViewHolder
import com.leonardoalves.ametest.store.viewmodel.BannerItemViewModel
import com.leonardoalves.ametest.store.viewmodel.StoreBannerViewModel
import com.leonardoalves.ametest.utils.crossFade
import kotlinx.android.synthetic.main.view_store_banner_list.view.*

const val STORE_BANNER_LIST_VIEW_ID = R.layout.view_store_banner_list

class StoreBannerViewHolder(itemView: View, val listener: Listener<BannerItemViewModel>) : ViewHolder<StoreBannerViewModel>(itemView) {
    override fun bind(viewModel: StoreBannerViewModel) {
        val numberOfItems = viewModel.bannerList.size
        val bannerAdapter = BannerAdapter(viewModel.bannerList)
        with(itemView){
            vpStoreBanner.adapter = bannerAdapter
            vpStoreBanner.offscreenPageLimit = numberOfItems
            if (numberOfItems <= 1){
                return
            }
            var isPageChangingAutomatically = false
            val handlerChangePage = Handler()
            val runnableChangePage = {
                val isLastItem = vpStoreBanner.currentItem == vpStoreBanner.childCount - 1
                val item = if (isLastItem) 0 else vpStoreBanner.currentItem + 1
                isPageChangingAutomatically = true
                vpStoreBanner.setCurrentItem(item, true)
            }
            val handlerChangePageDelay = 4000L
            handlerChangePage.postDelayed(runnableChangePage, handlerChangePageDelay)

            vpStoreBanner.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
                override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

                override fun onPageSelected(position: Int) {
                    handlerChangePage.removeCallbacksAndMessages(null)
                    if (isPageChangingAutomatically) {
                        handlerChangePage.postDelayed(runnableChangePage, handlerChangePageDelay)
                        isPageChangingAutomatically = false
                    } else {
                        handlerChangePage.postDelayed(runnableChangePage, 8000)
                    }
                }

                override fun onPageScrollStateChanged(state: Int) {}
            })

            with(piStoreBanner) {
                viewPager = vpStoreBanner
                types = viewModel.bannerList.map { PageIndicator.Type.Image }
                init()
            }
        }
    }

    override fun recycle() {}

    private inner class BannerAdapter(private val banners: List<BannerItemViewModel>) : PagerAdapter() {

        override fun instantiateItem(container: ViewGroup, position: Int): Any =
            ImageView(itemView.context).apply {
                val banner = banners[position]
                Glide.with(itemView.context).load(banner.image)
                    .crossFade()
                    .into(this)
                scaleType = ImageView.ScaleType.CENTER_CROP
                container.addView(this)
                setOnClickListener { listener.onClick(banner) }
            }

        override fun getCount() = banners.size

        override fun isViewFromObject(view: View, `object`: Any) = view === `object`

        override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
            Glide.with(itemView.context).clear(`object` as ImageView)
            container.removeView(`object`)
        }
    }
}