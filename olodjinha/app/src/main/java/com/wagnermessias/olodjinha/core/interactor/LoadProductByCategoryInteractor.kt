package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.ProductRepository
import com.wagnermessias.olodjinha.core.model.Products
import retrofit2.Response

class LoadProductByCategoryInteractor(
    private val repository: ProductRepository
) {
    suspend fun execute(categoryId: Int, offset: Int): Response<Products> {
        val call = repository.getProductsByCategory(categoryId, offset)
        return call.await()
    }
}