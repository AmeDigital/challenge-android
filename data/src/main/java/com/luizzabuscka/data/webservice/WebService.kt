package com.luizzabuscka.data.webservice


import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.http.POST
import retrofit2.http.Path


interface WebService {

    @GET("banner")
    fun getBanners() : Single<BannersResponse>

    @GET("categoria")
    fun getCategories() : Single<CategoriesResponse>

    @GET("produto/maisvendidos")
    fun getBestSellers() : Single<ProductsResponse>

    @GET("produto")
    fun getProducts(@Query("offset") offset: Int, @Query("limit") limit: Int, @Query("categoriaId") categoryId: Int) : Single<ProductsResponse>

    @POST("produto/{productId}")
    fun reserveProduct(@Path("productId") productId: Int) : Single<ResponseBody>

}

object WebServiceFactory {

    private const val baseUrl = "https://alodjinha.herokuapp.com/"

    internal fun create(): WebService {

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()

        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(client)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WebService::class.java)
    }
}