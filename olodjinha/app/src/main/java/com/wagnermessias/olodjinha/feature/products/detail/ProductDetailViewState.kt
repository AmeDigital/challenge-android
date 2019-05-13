package com.wagnermessias.olodjinha.feature.products.detail

import com.wagnermessias.olodjinha.core.model.ReservationResponse

sealed class ProductDetailViewState {
    data class ReservationProduct(val response: ReservationResponse): ProductDetailViewState()
    data class ReservationError(val value: String ): ProductDetailViewState()
    object ServerError : ProductDetailViewState()
    object NetworkError : ProductDetailViewState()
}
