package com.leonardoalves.repository.api

import com.leonardoalves.data.entity.Banner
import com.leonardoalves.data.entity.Category
import com.leonardoalves.data.entity.Product
import com.leonardoalves.data.response.Response
import io.reactivex.Completable
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/banner")
    fun getBannerList(): Flowable<Response<Banner>>

    @GET("/categoria")
    fun getCategoriesList(): Flowable<Response<Category>>

    @GET("/produto/maisvendidos")
    fun getBestSellers() : Flowable<Response<Product>>

    @GET("/produto/{id}")
    fun getProduct(@Path ("id") id: Int) : Flowable<Product>

    @POST("/produto/{id}")
    fun reserveProduct(@Path ("id") id: Int) : Completable

    @GET("/produto")
    fun getCategoryProducts(@Query("offset") offset:Int, @Query("limit") limit:Int, @Query("categoriaId") categoryId:Int): Flowable<Response<Product>>
}