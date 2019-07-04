package br.com.igorfs.lodjinha.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import br.com.igorfs.lodjinha.service.BannerService
import br.com.igorfs.lodjinha.util.RetrofitFactory
import br.com.igorfs.lodjinha.util.callback
import br.com.igorfs.lodjinha.vo.HomeBannerVo

class HomeViewModel: ViewModel() {

    private val bannerList = MutableLiveData<List<HomeBannerVo>>()
    private val bannerService: BannerService by lazy {
        RetrofitFactory().getRetrofit().create(BannerService::class.java)
    }

    companion object {
        private const val TAG = "HomeViewModel"
    }

    fun getBannerList(): LiveData<List<HomeBannerVo>> = bannerList


    fun fetchBannerList() {
        bannerService.listBanners().enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { bannerList.postValue(it.bannerList) }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })

    }
}