package br.com.sf.lojinha.repository.store.remote

import br.com.sf.lojinha.repository.*
import br.com.sf.lojinha.repository.store.StoreDataSource
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


class StoreRemoteDataSource : StoreDataSource {

    private val service: Service;

    init {
        service = createService()
    }

    override fun listAllBanners(): Single<List<Banner>> {
        return service.listAllBanners()
            .map { it.content }
    }

    override fun listAllCategories(): Single<List<Category>> {
        return service.listAllCategories()
            .map { it.content }
    }

    override fun findProductById(id: Int): Single<Product> {
        return service.findProductById(id)
    }

    override fun findProducts(offset: Int, limit: Int, category: Int): Single<List<Product>> {
        return service.findProducts(offset, limit, category)
            .map { it.content }
    }

    override fun listBestSellers(): Single<List<Product>> {
        return service.findBestSellers()
            .map { it.content }
    }

    override fun reserve(productId: Int): Single<Reserve> {
        return service.reserve(productId)
    }

    private fun createService(): Service {
        val retrofit = Retrofit.Builder()
            .baseUrl(SERVICE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(Service::class.java);
    }

    interface Service {

        @GET("/banner")
        fun listAllBanners(): Single<Data<Banner>>

        @GET("/categoria")
        fun listAllCategories(): Single<Data<Category>>

        @GET("/produto/{produtoId}")
        fun findProductById(@Path("produtoId") produtoId: Int): Single<Product>

        @GET("/produto")
        fun findProducts(
            @Query("offset") offset: Int,
            @Query("limit") limit: Int,
            @Query("category") category: Int
        ): Single<Data<Product>>

        @GET("/produto/maisvendidos")
        fun findBestSellers(): Single<Data<Product>>

        @POST("/produto/{produtoId}")
        fun reserve(@Path("produtoId") produtoId: Int): Single<Reserve>

    }

    companion object {
        const val SERVICE_URL = "https://alodjinha.herokuapp.com/"
    }
}