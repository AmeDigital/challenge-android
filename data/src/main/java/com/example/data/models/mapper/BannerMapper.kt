package com.example.data.models.mapper

import com.example.data.models.responses.banner.BannerResponse
import com.example.domain.models.Banner

object BannerMapper {
    fun mapFrom(response: List<BannerResponse>) =
        response.map {
            Banner(
                id = it.id,
                imageUrl = it.imageUrl,
                destinationUrl = it.destinationUrl
            )
        }
}