package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.models.ProductInfo
import com.example.domain.repositories.product.IProductListRepository
import io.reactivex.Observable

class ProductListUseCase(val repository: IProductListRepository) : ProductList {
    override fun execute(params: ProductInfo): Observable<List<Product>> {
        return repository.getProductList(params.page, params.itemsPerPage, params.categoryId)
    }
}