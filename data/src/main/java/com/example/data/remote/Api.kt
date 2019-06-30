package com.example.data.remote

import com.example.data.models.responses.ApiResponse
import com.example.data.models.responses.banner.BannerResponse
import com.example.data.models.responses.category.CategoryResponse
import com.example.data.models.responses.product.ProductResponse
import io.reactivex.Completable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("/banner")
    fun getBannerList(): Observable<ApiResponse<List<BannerResponse>>>

    @GET("/categoria")
    fun getCategoryList(): Observable<ApiResponse<List<CategoryResponse>>>

    @GET("/produto/maisvendidos")
    fun getBestSellerList(): Observable<ApiResponse<List<ProductResponse>>>

    @GET("/produto/{productId}")
    fun getProduct(
        @Path("productId") productId: Int
    ): Observable<ProductResponse>

    @POST("/produto/{productId}")
    fun reserveProduct(
        @Path("productId") productId: Int
    ): Completable

    @GET("/produto")
    fun getProductList(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("categoriaId") categoryId: Int
    ): Observable<ApiResponse<List<ProductResponse>>>
}