package com.amedigital.challenge_model.api

import com.amedigital.challenge_model.Banner
import com.amedigital.challenge_model.Categoria
import com.amedigital.challenge_model.Produto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LodjinhaApi {

    @GET("/banner")
    suspend fun getBanners(
    ): ApiDataResponse<List<Banner>>

    @GET("/categoria")
    suspend fun getCategorias(
    ): ApiDataResponse<List<Categoria>>

    @GET("/produto")
    suspend fun getProdutos(
    ): ApiDataResponse<List<Produto>>

    @GET("/produto/maisvendidos")
    suspend fun getMaisVendidos(
    ): ApiDataResponse<List<Produto>>

    @GET("/produto/{produtoId}")
    suspend fun getProduto(
        @Path("produtoId") produtoId: Long
    ): Produto

    @POST("/produto/{produtoId}")
    suspend fun reservarProduto(
        @Path("produtoId") produtoId: Long
    ): ApiResultResponse
}