package com.example.lodjinha.productList

import com.example.domain.models.Product
import com.example.domain.models.ProductInfo

object ProductListContract {
    interface View {
        fun showProductsList(list: List<Product>)
        fun showLoading()
        fun hideLoading()
    }

    interface Presenter {
        fun getProductsList(productInfo: ProductInfo)
    }
}