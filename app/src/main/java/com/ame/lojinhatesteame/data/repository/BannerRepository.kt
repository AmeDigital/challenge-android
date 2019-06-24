package com.ame.lojinhatesteame.data.repository

import android.support.annotation.MainThread
import com.ame.lojinhatesteame.data.model.Banner

interface BannerRepository {
    fun loadBanner(callbacks: BannerCallbacks)

    interface BannerCallbacks {
        @MainThread
        fun onLoadBannerSuccess(banners: MutableList<Banner>)

        @MainThread
        fun onLoadBannerError(messageError: String)
    }
}