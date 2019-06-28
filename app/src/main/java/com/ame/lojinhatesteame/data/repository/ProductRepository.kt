package com.ame.lojinhatesteame.data.repository

import android.support.annotation.MainThread
import com.ame.lojinhatesteame.data.model.Product

interface ProductRepository {
    fun loadProductsByCategory(categoryId: Int, callbacks: ProductCallbacks)
    fun loadProductsBestSales(callbacks: ProductCallbacks)
    fun reserve(productId: Int, callbacks: ProductCallbacks)

    interface ProductCallbacks {
        @MainThread
        fun onLoadProductByCategorySuccess(products: MutableList<Product>)

        @MainThread
        fun onLoadProductByCategoryError(errorMessage: String)

        @MainThread
        fun onLoadBestSalesSuccess(products: MutableList<Product>)

        @MainThread
        fun onLoadBestSalesError(errorMessage: String)

        @MainThread
        fun onReserveSuccess()

        @MainThread
        fun onReserveError(errorMessage: String)
    }
}