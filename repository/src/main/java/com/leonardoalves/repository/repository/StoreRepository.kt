package com.leonardoalves.repository.repository

import com.leonardoalves.repository.api.Api
import com.leonardoalves.repository.onDefaultSchedulers

class StoreRepository(private val api: Api) {
    fun getBanners() = api.getBannerList().onDefaultSchedulers().map {it.data?: listOf()}
    fun getCategories() = api.getCategoriesList().onDefaultSchedulers().map { it.data?: listOf() }
}