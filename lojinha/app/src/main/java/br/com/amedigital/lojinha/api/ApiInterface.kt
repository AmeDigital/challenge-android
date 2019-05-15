package com.br.cinesky.api

import br.com.amedigital.lojinha.api.response.ResultResponse
import br.com.amedigital.lojinha.model.*
import com.br.cinesky.model.BannerResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @GET("/banner")
    fun getBanner(): Observable<BannerResponse>

    @GET("/categoria")
    fun getCategoria(): Observable<CategoriaResponse>

    @GET("/produto")
    fun getProduto(@Query("categoriaId") id: String): Observable<ProdutoResponse>

    @GET("/produto/maisvendidos")
    fun getMaisVendidos(): Observable<MaisVendidoResponse>

    @GET("/produto/{produtoId}")
    fun getProdutoPorId( @Path("produtoId") id: Int): Observable<ProdutoResponse>

    @POST("/produto/{produtoId}")
    fun setProdutoPorId( @Path("produtoId") id: String): Observable<ResultResponse>
}