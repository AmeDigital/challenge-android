package com.wagnermessias.olodjinha.core.interactor

import com.wagnermessias.olodjinha.core.data.ProductRepository
import com.wagnermessias.olodjinha.core.model.ReservationResponse
import retrofit2.Response

class ReservationProductInteractor(
    private val repository: ProductRepository
) {
    suspend fun execute(productId: Int): Response<ReservationResponse> {
        val call = repository.reservationProduct(productId)
        return call.await()
    }
}