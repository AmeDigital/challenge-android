package com.lodjinha.remote

class LodjinhaDataSource(private val service: LodjinhaService) {

    fun getBannerAsync() = service.bannerAsync()

    fun getCategoriesAsync() = service.categoriesAsync()

    fun getProductsAsync(page: Int, categoryId: Int, limit: Int) = service.productsAsync(page, categoryId, limit)

    fun getTopSellingProductsAsync() = service.topSellingProductsAsync()

    fun getProductAsync(productId: Int) = service.productAsync(productId)

    fun bookProductAsync(productId: Int) = service.bookProductAsync(productId)
}
