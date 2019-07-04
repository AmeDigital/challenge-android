package br.com.igorfs.lodjinha.service

import br.com.igorfs.lodjinha.vo.BaseApiVo
import br.com.igorfs.lodjinha.vo.CategoryVo
import retrofit2.Call
import retrofit2.http.GET

interface CategoryService {

    @GET("categoria")
    fun categoryList(): Call<BaseApiVo<CategoryVo>>
}