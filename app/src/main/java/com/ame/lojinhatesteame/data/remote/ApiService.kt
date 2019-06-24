package com.ame.lojinhatesteame.data.remote

import com.ame.lojinhatesteame.data.remote.response.BodyResponseBanner
import com.ame.lojinhatesteame.data.remote.response.BodyResponseCategory
import com.ame.lojinhatesteame.data.remote.response.BodyResponseProduct
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("banner")
    fun getBanner(): Call<BodyResponseBanner>

    @GET("categoria")
    fun getCategory(): Call<BodyResponseCategory>

    @GET("produto")
    fun getProductByCategory(@Query("categoriaId") id: Int): Call<BodyResponseProduct>

    @GET("produto/maisvendidos")
    fun getProductsBestSales(): Call<BodyResponseProduct>

    @POST("produto/{produtoId}")
    fun setReserve(@Path("produtoId") id: Int) : Call<Void>
}