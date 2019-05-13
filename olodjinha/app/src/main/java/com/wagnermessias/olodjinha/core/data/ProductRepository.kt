package com.wagnermessias.olodjinha.core.data

import com.wagnermessias.olodjinha.core.data.remote.ALodjinhaApi
import com.wagnermessias.olodjinha.core.model.Products
import com.wagnermessias.olodjinha.core.model.ReservationResponse
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response

class ProductRepository(
    private val aLodjinhaApi: ALodjinhaApi
) {

    suspend fun getBestSellers(): Deferred<Response<Products>> = withContext(Dispatchers.IO) {
        aLodjinhaApi.getProductsBestSellers()
    }

    suspend fun getProductsByCategory(categoryId: Int, offset: Int): Deferred<Response<Products>> =
        withContext(Dispatchers.IO) {
            aLodjinhaApi.getProductsByCategory(categoryId, offset, LIMIT)
        }

    suspend fun reservationProduct(productId: Int): Deferred<Response<ReservationResponse>> =
        withContext(Dispatchers.IO) {
            aLodjinhaApi.reservationProduct(productId)
        }

    companion object{
        const val LIMIT = 20

    }
}