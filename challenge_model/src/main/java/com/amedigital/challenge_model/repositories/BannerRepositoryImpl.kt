package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource

open class BannerRepositoryImpl(val api: LodjinhaApi) : BaseRepositoryImpl(), BannerRepository {

    override suspend fun getBanners(): Resource<List<Banner>> {
        return safeCallForApiResponse {
            api.getBanners()
        }
    }
}