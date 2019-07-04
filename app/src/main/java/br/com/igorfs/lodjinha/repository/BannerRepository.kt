package br.com.igorfs.lodjinha.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.igorfs.lodjinha.service.BannerService
import br.com.igorfs.lodjinha.util.RetrofitFactory
import br.com.igorfs.lodjinha.util.callback
import br.com.igorfs.lodjinha.vo.HomeBannerVo

class BannerRepository {

    private val bannerList = MutableLiveData<List<HomeBannerVo>>()
    private val bannerService: BannerService by lazy {
        RetrofitFactory().getRetrofit().create(BannerService::class.java)
    }

    companion object {
        private const val TAG = "BannerRepository"
    }

    fun getBannerList(): LiveData<List<HomeBannerVo>> = bannerList


    fun fetchBannerList() {
        bannerService.listBanners().enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { bannerList.postValue(it.getList) }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })

    }
}