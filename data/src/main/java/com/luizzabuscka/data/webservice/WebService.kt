package com.luizzabuscka.data.webservice


import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface WebService {

    @GET("banner")
    fun getBanners() : Single<BannersResponse>

    @GET("categoria")
    fun getCategories() : Single<CategoriesResponse>

    @GET("produto/maisvendidos")
    fun getBestSellers() : Single<ProductsResponse>

}

object WebServiceFactory {

    private const val baseUrl = "https://alodjinha.herokuapp.com/"

    internal fun create(): WebService {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}