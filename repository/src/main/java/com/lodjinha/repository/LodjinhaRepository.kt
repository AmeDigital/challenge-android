package com.lodjinha.repository

import androidx.lifecycle.LiveData
import com.lodjinha.model.Banners
import com.lodjinha.model.Categories
import com.lodjinha.model.Product
import com.lodjinha.model.Products
import com.lodjinha.repository.utils.Resource

interface LodjinhaRepository {
    suspend fun getBannerAsync(shouldFetch: Boolean = true) : LiveData<Resource<Banners>>

    suspend fun getCategoriesAsync(shouldFetch: Boolean = true) : LiveData<Resource<Categories>>

    suspend fun getProductsAsync(page: Int, categoryId: Int, limit: Int = 20, shouldFetch: Boolean = true) : LiveData<Resource<Products>>

    suspend fun getTopSellingProductsAsync(shouldFetch: Boolean = true) : LiveData<Resource<Products>>

    suspend fun getProductAsync(productId: Int, shouldFetch: Boolean = true) : LiveData<Resource<Product>>

//    suspend fun bookProductAsync(productId: Int, shouldFetch: Boolean = true)
}
