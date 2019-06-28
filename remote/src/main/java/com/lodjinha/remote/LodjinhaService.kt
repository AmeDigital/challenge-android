package com.lodjinha.remote

import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Product
import com.lodjinha.model.Products
import kotlinx.coroutines.Deferred
import retrofit2.http.*

interface LodjinhaService {

    @POST("banner")
    fun bannerAsync(): Deferred<Banners>

    @GET("categoria")
    fun categoriesAsync(): Deferred<Categories>

    @GET("produto")
    fun productsAsync(): Deferred<Products>

    @GET("produto/maisvendidos")
    fun topSellingProductsAsync(): Deferred<Products>

    @GET("produto/{productId}")
    fun productAsync(@Path("productId") productId: Int): Deferred<Product>

    @POST("produto/{productId}")
    fun bookProductAsync(@Path("productId") productId: Int)
}
