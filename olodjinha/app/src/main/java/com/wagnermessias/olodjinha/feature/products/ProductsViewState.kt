package com.wagnermessias.olodjinha.feature.products

import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.core.model.ReservationResponse

sealed class ProductsViewState {
    data class ProductsList(val products: ArrayList<Product>): ProductsViewState()
    data class ReservationProduct(val response: ReservationResponse): ProductsViewState()
    data class ShowErro(val value: Boolean ): ProductsViewState()
}
