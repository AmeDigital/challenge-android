package br.com.amedigital.network

import br.com.amedigital.network.model.BodyRequestBanner
import br.com.amedigital.network.model.BodyRequestCategory
import br.com.amedigital.network.model.BodyRequestProduct
import retrofit2.Call
import retrofit2.http.*

interface LojinhaService  {

    @GET("banner")
    fun getBanner(): Call<BodyRequestBanner>

    @GET("categoria")
    fun getCategoria(): Call<BodyRequestCategory>

    @GET("produto")
    fun getProdutoPorCategoria(@Query("categoriaId") id: Int): Call<BodyRequestProduct>

    @GET("produto/maisvendidos")
    fun getProdutosMaisVendidos(): Call<BodyRequestProduct>

    @POST("produto/{produtoId}")
    fun setReserva(@Path("produtoId") id: Int) : Call<Void>
}