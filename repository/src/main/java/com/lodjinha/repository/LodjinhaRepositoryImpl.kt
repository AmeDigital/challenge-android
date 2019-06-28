package com.lodjinha.repository

import androidx.lifecycle.LiveData
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Product
import com.lodjinha.model.Products
import com.lodjinha.remote.LodjinhaDataSource
import com.lodjinha.repository.utils.NetworkBoundResource
import com.lodjinha.repository.utils.Resource
import kotlinx.coroutines.Deferred

class LodjinhaRepositoryImpl(private val dataSource: LodjinhaDataSource) : LodjinhaRepository {

    override suspend fun getBannerAsync(shouldFetch: Boolean): LiveData<Resource<Banners>> =
        object : NetworkBoundResource<Banners, Banners>() {
            override fun processResponse(response: Banners): Banners = response

            override fun shouldFetch(): Boolean = true

            override fun createCallAsync(): Deferred<Banners> = dataSource.getBannerAsync()
        }.build().asLiveData()

    override suspend fun getCategoriesAsync(shouldFetch: Boolean): LiveData<Resource<Categories>> =
        object : NetworkBoundResource<Categories, Categories>() {
            override fun processResponse(response: Categories): Categories = response

            override fun shouldFetch(): Boolean = true

            override fun createCallAsync(): Deferred<Categories> = dataSource.getCategoriesAsync()
        }.build().asLiveData()

    override suspend fun getProductsAsync(shouldFetch: Boolean): LiveData<Resource<Products>> =
        object : NetworkBoundResource<Products, Products>() {
            override fun processResponse(response: Products): Products = response

            override fun shouldFetch(): Boolean = shouldFetch

            override fun createCallAsync(): Deferred<Products> = dataSource.getProductsAsync()
        }.build().asLiveData()

    override suspend fun getTopSellingProductsAsync(shouldFetch: Boolean): LiveData<Resource<Products>> =
        object : NetworkBoundResource<Products, Products>() {
            override fun processResponse(response: Products): Products = response

            override fun shouldFetch(): Boolean = shouldFetch

            override fun createCallAsync(): Deferred<Products> = dataSource.getTopSellingProductsAsync()
        }.build().asLiveData()

    override suspend fun getProductAsync(productId: Int, shouldFetch: Boolean): LiveData<Resource<Product>> =
        object : NetworkBoundResource<Product, Product>() {
            override fun processResponse(response: Product): Product = response

            override fun shouldFetch(): Boolean = shouldFetch

            override fun createCallAsync(): Deferred<Product> = dataSource.getProductAsync(productId)
        }.build().asLiveData()

//    override suspend fun bookProductAsync(productId: Int, shouldFetch: Boolean) =
//        object : NetworkBoundResource<Product, Product>() {
//            override fun processResponse(response: Product): Product = response
//
//            override fun shouldFetch(): Boolean = shouldFetch
//
//            override fun createCallAsync(): Deferred<Product> {
//                return null
//            }
//        }.build().asLiveData()
}
