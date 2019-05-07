package br.com.sf.lojinha.repository.store

import br.com.sf.lojinha.repository.Banner
import br.com.sf.lojinha.repository.Category
import br.com.sf.lojinha.repository.Product
import br.com.sf.lojinha.repository.Reserve
import io.reactivex.Completable
import io.reactivex.Single

interface StoreDataSource {

    fun listAllBanners(): Single<List<Banner>>

    fun listAllCategories(): Single<List<Category>>

    fun findProductById(id: Int): Single<Product>

    fun findProducts(offset: Int, limit: Int, category: Int): Single<List<Product>>

    fun listBestSellers(): Single<List<Product>>

    fun reserve(productId: Int): Single<Reserve>

}