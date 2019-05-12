package com.wagnermessias.olodjinha.core.data

import com.wagnermessias.olodjinha.core.data.remote.ALodjinhaApi
import com.wagnermessias.olodjinha.core.model.Banners
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class BannerRepository(
    private val aLodjinhaApi: ALodjinhaApi
) {
    suspend fun execute(): Deferred<Response<Banners>> = withContext(Dispatchers.IO) {
        aLodjinhaApi.getBanners()
    }
}