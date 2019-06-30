package com.example.data.repositories.bestSeller

import com.example.data.models.mapper.BestSellerMapper
import com.example.data.remote.Api
import com.example.domain.models.Product
import com.example.domain.repositories.bestSeller.IBestSellerListRepository
import io.reactivex.Observable

class BestSellerRepository(val api: Api) : IBestSellerListRepository {
    override fun getBestSellerList(): Observable<List<Product>> {
        return api.getBestSellerList().map{
            BestSellerMapper.mapFrom( it.results)
        }
    }
}