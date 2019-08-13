package com.luizzabuscka.data.mapper

import com.luizzabuscka.commons.model.Banner
import com.luizzabuscka.data.webservice.BannersResponse

class BannersMapper {

    fun transform(response: BannersResponse): List<Banner> {
        val banners = arrayListOf<Banner>()
        for (bannerWS in response.banners) {
            banners.add(Banner(bannerWS.urlImage, bannerWS.urlLink))
        }
        return banners
    }

}