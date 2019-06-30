package com.example.domain.repositories.product

import com.example.domain.models.Product
import io.reactivex.Observable

interface IProductRepository {
    fun getProduct(productId: Int): Observable<Product>
}