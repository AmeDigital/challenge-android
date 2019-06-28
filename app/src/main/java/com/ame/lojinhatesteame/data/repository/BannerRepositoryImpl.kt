package com.ame.lojinhatesteame.data.repository

import com.ame.lojinhatesteame.LojinhaApplication
import com.ame.lojinhatesteame.R
import com.ame.lojinhatesteame.data.executor.disk
import com.ame.lojinhatesteame.data.executor.network
import com.ame.lojinhatesteame.data.executor.ui
import com.ame.lojinhatesteame.data.model.Banner
import com.ame.lojinhatesteame.data.remote.ApiService
import com.ame.lojinhatesteame.data.remote.response.BodyResponseBanner
import retrofit2.Response
import java.io.IOException
import javax.inject.Inject

class BannerRepositoryImpl @Inject constructor(private val apiService: ApiService) : BannerRepository {

    override fun loadBanner(callbacks: BannerRepository.BannerCallbacks) {
        network {
            try {
                val bannerResponse: Response<BodyResponseBanner> = apiService.getBanner().execute()
                if (bannerResponse.isSuccessful) {
                    val responseBody = bannerResponse.body()
                    val banners = mutableListOf<Banner>()
                    if (responseBody != null) {
                        disk {
                            responseBody.apply {
                                data.forEach {
                                    banners.add(it)
                                }
                                ui{callbacks.onLoadBannerSuccess(banners)}
                            }
                        }
                    } else {
                       ui{ callbacks.onLoadBannerError(LojinhaApplication.instance.getString(R.string.unexpected_error))}
                    }
                } else {
                    ui{callbacks.onLoadBannerError(bannerResponse.errorBody().toString())}
                }
            } catch (error : IOException) {
                error.message?.apply {
                    ui{callbacks.onLoadBannerError(this)}
                }
                error.stackTrace
            }
        }
    }
}