package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.BannerRepository
import com.wagnermessias.olodjinha.core.model.Banners
import retrofit2.Response

class BannerInteractor(
    private val repository: BannerRepository
){
    suspend fun getBanners(): Response<Banners> {
        val call = repository.execute()
        return call.await()

//        return if (response.isSuccessful) {
//            response.body()
//        } else {
//            null
//        }
    }
}