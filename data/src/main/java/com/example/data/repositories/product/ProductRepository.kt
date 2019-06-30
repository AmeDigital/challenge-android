package com.example.data.repositories.product

import com.example.data.models.mapper.ProductMapper
import com.example.data.remote.Api
import com.example.domain.models.Product
import com.example.domain.repositories.product.IProductRepository
import io.reactivex.Observable

class ProductRepository(val api: Api) : IProductRepository {
    override fun getProduct(productId: Int): Observable<Product> {
        return api.getProduct(productId).map{
            ProductMapper.map(it)
        }
    }
}