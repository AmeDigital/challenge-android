package com.example.data.repositories.product

import com.example.data.models.mapper.ProductListMapper
import com.example.data.remote.Api
import com.example.domain.models.Product
import com.example.domain.repositories.product.IProductListRepository
import io.reactivex.Observable

class ListProductRepository(val api: Api) : IProductListRepository {
    override fun getProductList(offset: Int, limit: Int, categoryId: Int): Observable<List<Product>> {
        return api.getProductList(categoryId = categoryId, offset = offset, limit = 7).map {
            ProductListMapper.mapFrom(it.results)
        }
    }
}
