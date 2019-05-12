package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.ProductRepository
import com.wagnermessias.olodjinha.core.model.Products
import retrofit2.Response

class LoadBestSellersInteractor(
    private val repository: ProductRepository
) {
    suspend fun execute(): Response<Products> {
        val call = repository.getBestSellers()
        return call.await()
    }
}