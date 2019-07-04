package br.com.igorfs.lodjinha.service

import br.com.igorfs.lodjinha.vo.BaseApiVo
import br.com.igorfs.lodjinha.vo.ProductVo
import retrofit2.Call
import retrofit2.http.GET

interface ProductService {

    @GET("produto/maisvendidos")
    fun getTopSellers(): Call<BaseApiVo<ProductVo>>
}