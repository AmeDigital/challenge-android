package marco.challenge_android.data

import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface LojinhaAPI{

    @GET("/produto")
    fun getProducts(
        @Query("offset") offset: Int,
        @Query("limit") limit: Int,
        @Query("categoriaId") categoriaId: Int?
    ): Observable<ProductResponse>

    @GET("/produto/maisvendidos")
    fun getProductsBestSellers(): Observable<ProductResponse>

    @POST("/produto/{produtoId}")
    fun reserveProduct(
        @Path("produtoId") produtoId: Int?
    ): Observable<Response<Void>>

    @GET("/banner")
    fun getBanners(): Observable<BannerResponse>

    @GET("/categoria")
    fun getCategories(): Observable<CategoryResponse>


}