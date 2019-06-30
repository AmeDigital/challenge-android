package br.com.igorfs.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.igorfs.lodjinha.repository.BannerRepository
import br.com.igorfs.lodjinha.vo.HomeBannerVo

class HomeViewModel: ViewModel() {

    private val bannerList = MutableLiveData<List<HomeBannerVo>>()

    fun fetchHomeBanner() {
        bannerList.postValue(BannerRepository().getBannerList())
    }

    fun getBannerList(): LiveData<List<HomeBannerVo>> = bannerList
}