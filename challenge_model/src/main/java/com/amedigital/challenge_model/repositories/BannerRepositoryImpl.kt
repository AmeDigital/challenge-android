package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.api.LodjinhaApi
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.api.SafeApi

open class BannerRepositoryImpl(val api: LodjinhaApi) : BannerRepository {

    override suspend fun getBanner(): Resource<List<Banner>> {
        val call = SafeApi.safeCall {
            api.getBanner()
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value.data)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }

}