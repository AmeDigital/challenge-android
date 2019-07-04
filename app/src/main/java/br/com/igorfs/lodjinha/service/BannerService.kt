package br.com.igorfs.lodjinha.service

import br.com.igorfs.lodjinha.vo.BannerApiVo
import br.com.igorfs.lodjinha.vo.HomeBannerVo
import retrofit2.Call
import retrofit2.http.GET

interface BannerService {

    @GET("banner")
    fun listBanners(): Call<BannerApiVo>
}