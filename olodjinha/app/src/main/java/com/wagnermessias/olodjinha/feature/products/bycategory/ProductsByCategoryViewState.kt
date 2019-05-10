package com.wagnermessias.olodjinha.feature.products.bycategory

import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.core.model.ReservationResponse

sealed class ProductsByCategoryViewState {
    data class ProductsByCategoryList(val products: ArrayList<Product>): ProductsByCategoryViewState()
    data class ShowErro(val value: Boolean ): ProductsByCategoryViewState()
}
