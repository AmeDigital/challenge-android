package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.api.Resource

interface BannerRepository {
    suspend fun getBanners(): Resource<List<Banner>>
}