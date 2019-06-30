package com.example.domain.repositories.product

import com.example.domain.models.Product
import io.reactivex.Observable

interface IProductListRepository {
    fun getProductList(offset: Int, limit: Int, categoryId: Int): Observable<List<Product>>
}