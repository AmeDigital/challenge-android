package com.lodjinha.remote

import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Product
import com.lodjinha.model.Products
import kotlinx.coroutines.Deferred
import okhttp3.ResponseBody
import retrofit2.http.*

interface LodjinhaService {

    @GET("banner")
    fun bannerAsync(): Deferred<Banners>

    @GET("categoria")
    fun categoriesAsync(): Deferred<Categories>

    @GET("produto")
    fun productsAsync(
        @Query("offset") page: Int,
        @Query("categoriaId") categoryId: Int,
        @Query("limit") limit: Int
    ): Deferred<Products>

    @GET("produto/maisvendidos")
    fun topSellingProductsAsync(): Deferred<Products>

    @GET("produto/{productId}")
    fun productAsync(@Path("productId") productId: Int): Deferred<Product>

    @POST("produto/{productId}")
    fun reserveProductAsync(@Path("productId") productId: Int): Deferred<ResponseBody>
}
