package com.wagnermessias.olodjinha.core.data.remote

import com.wagnermessias.olodjinha.core.model.Banners
import com.wagnermessias.olodjinha.core.model.Categories
import com.wagnermessias.olodjinha.core.model.Products
import com.wagnermessias.olodjinha.core.model.ReservationResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ALodjinhaApi {
    companion object {
        private const val BANNER = "/banner"
        private const val CATEGORY = "/categoria"
        private const val BEST_SELLERS = "/produto/maisvendidos"
        private const val PRODUCTS = "/produto"
        private const val PRODUTO_ID = "produtoId"
        private const val RESERVATION = "/produto/{$PRODUTO_ID}"
    }

    @GET(BANNER)
    fun getBanners(): Deferred<Response<Banners>>

    @GET(CATEGORY)
    fun getCategories(): Deferred<Response<Categories>>

    @GET(BEST_SELLERS)
    fun getProductsBestSellers(): Deferred<Response<Products>>

    @GET(PRODUCTS)
    fun getProductsByCategory(@Query("categoriaId") categoriaId: Int,
                              @Query("offset") offset: Int,
                              @Query("limit") limit: Int ): Deferred<Response<Products>>

    @POST(RESERVATION)
    fun reservationProduct(@Path(PRODUTO_ID) id: Int): Deferred<Response<ReservationResponse>>
}