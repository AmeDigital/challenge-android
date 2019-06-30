package com.example.domain.usecases.product

import com.example.domain.models.Product
import com.example.domain.repositories.product.IProductRepository
import io.reactivex.Observable

class ProductUseCase(val repository: IProductRepository) : com.example.domain.usecases.product.Product {
    override fun execute(params: Int): Observable<Product> {
        return repository.getProduct(params)
    }
}