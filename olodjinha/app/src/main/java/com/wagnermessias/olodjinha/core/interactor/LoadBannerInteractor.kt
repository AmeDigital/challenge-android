package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.BannerRepository
import com.wagnermessias.olodjinha.core.model.Banners
import retrofit2.Response

class LoadBannerInteractor(
    private val repository: BannerRepository
) {
    suspend fun execute(): Response<Banners> {
        val call = repository.execute()
        return call.await()
    }
}