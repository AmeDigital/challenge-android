package com.example.data.repositories.product

import com.example.data.remote.Api
import com.example.domain.repositories.product.IReserveProductRepository
import io.reactivex.Completable

class ReserveProductRepository(val api: Api) : IReserveProductRepository {
    override fun reserveProduct(productId: Int): Completable {
        return api.reserveProduct(productId)
    }
}