package com.wagnermessias.olodjinha.feature.products.bycategory

import com.wagnermessias.olodjinha.core.model.Product

sealed class ProductsByCategoryViewState {
    data class ProductsByCategoryList(val products: ArrayList<Product>): ProductsByCategoryViewState()
    object NetworkError : ProductsByCategoryViewState()
    object ServerError : ProductsByCategoryViewState()
    object EmptyList : ProductsByCategoryViewState()
}
