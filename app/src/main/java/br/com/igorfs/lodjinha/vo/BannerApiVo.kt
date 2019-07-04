package br.com.igorfs.lodjinha.vo

import com.google.gson.annotations.SerializedName

class BannerApiVo (

    @SerializedName("data")
    val bannerList: List<HomeBannerVo>
)