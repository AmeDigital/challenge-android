package com.leonardoalves.repository.repository

import com.leonardoalves.repository.api.Api
import com.leonardoalves.repository.onDefaultSchedulers

class StoreRepository(private val api: Api) {
    fun getBanners() = api.getBannerList().onDefaultSchedulers().map {it.data?: listOf()}
    fun getCategories() = api.getCategoriesList().onDefaultSchedulers().map { it.data?: listOf() }
    fun getBestSellers() = api.getBestSellers().onDefaultSchedulers().map { it.data?: listOf() }
    fun getCategoryProducts(offset:Int, limit:Int, categoryId:Int) =
        api.getCategoryProducts(offset, limit, categoryId).onDefaultSchedulers().map { it.data?: listOf() }
    fun getProduct(productId: Int) = api.getProduct(productId).onDefaultSchedulers()
    fun reserveProduct(productId: Int) = api.reserveProduct(productId).onDefaultSchedulers()
}