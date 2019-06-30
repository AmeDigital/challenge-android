package com.example.domain.repositories.product

import io.reactivex.Completable

interface IReserveProductRepository {
    fun reserveProduct(productId: Int): Completable
}