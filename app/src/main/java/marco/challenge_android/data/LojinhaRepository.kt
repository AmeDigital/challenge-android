package marco.challenge_android.data

import io.reactivex.Observable
import retrofit2.Response

open class LojinhaRepository(private var lojinhaService: LojinhaService): LojinhaAPI{

    private fun getService() = lojinhaService.getService()

    override fun getBanners(): Observable<BannerResponse> {
        return getService().getBanners()
    }

    override fun getCategories(): Observable<CategoryResponse> {
        return getService().getCategories()
    }

    override fun getProducts(offset: Int, limit: Int, categoriaId: Int?): Observable<ProductResponse> {
        return getService().getProducts(offset, limit, categoriaId)
    }

    override fun getProductsBestSellers(): Observable<ProductResponse> {
        return getService().getProductsBestSellers()
    }

    override fun reserveProduct(produtoId: Int?): Observable<Response<Void>> {
        return getService().reserveProduct(produtoId)
    }

}