package br.com.igorfs.lodjinha.service

import br.com.igorfs.lodjinha.vo.BaseApiVo
import br.com.igorfs.lodjinha.vo.ProductVo
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductService {

    companion object {
        const val DEFAULT_LIMIT = 20L
    }

    @GET("produto/maisvendidos")
    fun getTopSellers(): Call<BaseApiVo<ProductVo>>

    @GET("produto")
    fun getProducts(
        @Query("categoriaId") categoryId: Long?,
        @Query("limit") limit: Long = DEFAULT_LIMIT,
        @Query("offset") offset: Long?
        ): Call<BaseApiVo<ProductVo>>
}