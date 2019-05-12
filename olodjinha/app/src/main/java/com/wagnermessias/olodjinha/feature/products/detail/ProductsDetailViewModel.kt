package com.wagnermessias.olodjinha.feature.products.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.ReservationProductInteractor
import kotlinx.coroutines.launch
import java.io.IOException

class ProductsDetailViewModel(
    private val reservationProduct: ReservationProductInteractor

) : BaseViewModel() {
    private val state = MutableLiveData<ProductsDetailViewState>()
    val detailViewState: LiveData<ProductsDetailViewState> = state

    fun reservationProducts(productId: Int) {
        launch {
            try {
                val responseProducts = reservationProduct.execute(productId)

                if (responseProducts.isSuccessful) {

                    val response = responseProducts.body()

                    if (response != null) {

                        if (response.result.equals("success")) {
                            state.value = ProductsDetailViewState.ReservationProduct(response)
                        } else {
                            state.value = ProductsDetailViewState.ReservationError(response.mensagem)
                        }
                    }
                } else {
                    state.value = ProductsDetailViewState.ServerError
                }
            } catch (e: IOException) {
                state.value = ProductsDetailViewState.NetworkError
            }
        }
    }
}