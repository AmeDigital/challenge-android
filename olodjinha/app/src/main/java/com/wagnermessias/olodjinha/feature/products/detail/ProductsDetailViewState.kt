package com.wagnermessias.olodjinha.feature.products.detail

import com.wagnermessias.olodjinha.core.model.Product
import com.wagnermessias.olodjinha.core.model.ReservationResponse

sealed class ProductsDetailViewState {
    data class ReservationProduct(val response: ReservationResponse): ProductsDetailViewState()
    data class ShowErro(val value: Boolean ): ProductsDetailViewState()
}
