package com.example.domain.repositories.bestSeller

import com.example.domain.models.Product
import io.reactivex.Observable

interface IBestSellerListRepository {
    fun getBestSellerList(): Observable<List<Product>>
}