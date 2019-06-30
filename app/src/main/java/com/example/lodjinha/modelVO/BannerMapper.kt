package com.example.lodjinha.modelVO

import com.example.domain.models.Banner

fun Banner.map() =
    BannerVO(
        id = id,
        imageUrl = imageUrl,
        destinationUrl = destinationUrl
    )
