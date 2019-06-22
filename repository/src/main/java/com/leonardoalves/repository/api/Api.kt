package com.leonardoalves.repository.api

import com.leonardoalves.data.entity.Banner
import com.leonardoalves.data.entity.Category
import com.leonardoalves.data.response.Response
import io.reactivex.Flowable
import retrofit2.http.GET

interface Api {
    @GET("/banner")
    fun getBannerList(): Flowable<Response<Banner>>

    @GET("/categoria")
    fun getCategoriesList(): Flowable<Response<Category>>
}