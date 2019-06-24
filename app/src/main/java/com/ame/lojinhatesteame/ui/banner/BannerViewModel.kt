package com.ame.lojinhatesteame.ui.banner

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableBoolean
import com.ame.lojinhatesteame.data.model.Banner
import com.ame.lojinhatesteame.data.repository.BannerRepository
import com.ame.lojinhatesteame.util.SingleLiveEvent
import javax.inject.Inject

class BannerViewModel @Inject constructor(private val bannerRepository: BannerRepository) : ViewModel() {

    var bannerLiveData = MutableLiveData<MutableList<Banner>>()
    val isProgressBarVisible = ObservableBoolean()
    val showErrorToast = SingleLiveEvent<String>()


    init {
        isProgressBarVisible.set(false)
    }

    fun onLoadBanners() {
        isProgressBarVisible.set(true)
        bannerRepository.loadBanner(BannerCallbacks())
    }

    inner class BannerCallbacks : BannerRepository.BannerCallbacks {

        override fun onLoadBannerSuccess(banners: MutableList<Banner>) {
            isProgressBarVisible.set(false)
            bannerLiveData.value = banners.toMutableList()
        }

        override fun onLoadBannerError(messageError: String) {
            isProgressBarVisible.set(false)
            showErrorToast.value = messageError
        }
    }
}