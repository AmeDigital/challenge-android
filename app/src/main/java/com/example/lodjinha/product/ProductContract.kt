package com.example.lodjinha.product

import com.example.domain.models.Product

object ProductContract {
    interface View {
        fun showProduct(product: Product)
        fun showSuccessAlert()
        fun showErrorAlert()
    }

    interface Presenter {
        fun getProduct(productId: Int)
        fun reserveProduct(productId: Int)
    }
}