package com.wagnermessias.olodjinha.feature.products.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.ReservationProductInteractor
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ProductDetailViewModel(
    private val reservationProduct: ReservationProductInteractor,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseViewModel(mainDispatcher) {
    private val state = MutableLiveData<ProductDetailViewState>()
    val detailViewState: LiveData<ProductDetailViewState> = state

    fun reservationProducts(productId: Int) {
        scope.launch {
            try {
                val responseProducts = reservationProduct.execute(productId)

                if (responseProducts.isSuccessful) {

                    val response = responseProducts.body()

                    if (response != null) {

                        if (response.result.equals("success")) {
                            state.value = ProductDetailViewState.ReservationProduct(response)
                        } else {
                            state.value = ProductDetailViewState.ReservationError(response.mensagem)
                        }
                    }
                } else {
                    state.value = ProductDetailViewState.ServerError
                }
            } catch (e: IOException) {
                state.value = ProductDetailViewState.NetworkError
            }
        }
    }
}