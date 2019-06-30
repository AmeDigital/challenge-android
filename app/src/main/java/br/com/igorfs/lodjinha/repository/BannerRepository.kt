package br.com.igorfs.lodjinha.repository

import android.util.Log
import br.com.igorfs.lodjinha.service.BannerService
import br.com.igorfs.lodjinha.util.RetrofitFactory
import br.com.igorfs.lodjinha.util.callback
import br.com.igorfs.lodjinha.vo.HomeBannerVo

class BannerRepository {

    companion object {
        private const val TAG = "BannerRepository"
    }

    private val bannerService: BannerService by lazy {
        RetrofitFactory().getRetrofit().create(BannerService::class.java)
    }

    fun getBannerList(): List<HomeBannerVo> {
        var result: List<HomeBannerVo> = emptyList()

        bannerService.listBanners().enqueue(callback { response, throwable ->
            response?.let {
                response.body()?.let { result = it.bannerList }
            }
            throwable?.let {
                Log.e(TAG, it.message)
            }
        })

        return result
    }
}