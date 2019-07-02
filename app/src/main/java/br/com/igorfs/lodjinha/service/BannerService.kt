package br.com.igorfs.lodjinha.service

import br.com.igorfs.lodjinha.vo.BaseApiVo
import br.com.igorfs.lodjinha.vo.HomeBannerVo
import retrofit2.Call
import retrofit2.http.GET

interface BannerService {

    @GET("banner")
    fun listBanners(): Call<BaseApiVo<HomeBannerVo>>
}