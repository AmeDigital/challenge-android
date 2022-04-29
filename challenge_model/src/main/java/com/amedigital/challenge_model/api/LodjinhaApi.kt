package com.amedigital.challenge_model.api

import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface LodjinhaApi {

    @GET("/banner")
    suspend fun getBanners(
    ): ApiResponse<List<Banner>>

    @GET("/categoria")
    suspend fun getCategorias(
    ): ApiResponse<List<Categoria>>

    @GET("/produto/maisvendidos")
    suspend fun getMaisVendidos(
    ): ApiResponse<List<Produto>>

    @GET("/produto/{produtoId}")
    suspend fun getProduto(
        @Query("produtoId") produtoId: Int
    ): ApiResponse<Produto>

    @POST("/produto/{produtoId}")
    suspend fun reservarProduto(
        @Query("produtoId") produtoId: Long, @Body produto: Produto
    ): ApiResult

}