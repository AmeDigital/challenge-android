package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.ProductRepository
import com.wagnermessias.olodjinha.core.model.Products
import com.wagnermessias.olodjinha.core.model.ReservationResponse
import retrofit2.Response

class ProductInteractor(
    private val repository: ProductRepository
){
    suspend fun getBestSellers(): Response<Products> {
        val call = repository.getBestSellers()
        return call.await()
    }

    suspend fun getProductsByCategory(categoryId: Int, offset: Int): Response<Products> {
        val call = repository.getProductsByCategory(categoryId,offset)
        return call.await()
    }

    suspend fun reservationProduct(productId: Int): Response<ReservationResponse> {
        val call = repository.reservationProduct(productId)
        return call.await()
    }
}