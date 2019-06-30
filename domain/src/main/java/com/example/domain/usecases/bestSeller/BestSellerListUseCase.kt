package com.example.domain.usecases.bestSeller

import com.example.domain.models.Product
import com.example.domain.repositories.bestSeller.IBestSellerListRepository
import io.reactivex.Observable

class BestSellerListUseCase(val repository: IBestSellerListRepository) : BestSellerList {
    override fun execute(): Observable<List<Product>> {
        return repository.getBestSellerList()
    }
}