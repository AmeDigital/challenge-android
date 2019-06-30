package com.example.domain.usecases.product

import com.example.domain.repositories.product.IReserveProductRepository
import io.reactivex.Completable

class ReserveProductUseCase(val repository: IReserveProductRepository) : ReserveUseCase {
    override fun execute(params: Int): Completable {
        return repository.reserveProduct(params)
    }
}