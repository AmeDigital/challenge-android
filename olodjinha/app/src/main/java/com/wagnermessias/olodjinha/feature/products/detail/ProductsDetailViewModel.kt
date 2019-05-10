package com.wagnermessias.olodjinha.feature.products.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.ReservationProductInteractor
import com.wagnermessias.olodjinha.core.model.Product
import kotlinx.coroutines.launch

class ProductsDetailViewModel(
    private val reservationProduct: ReservationProductInteractor

) : BaseViewModel() {
    private val lastProducts: ArrayList<Product> = ArrayList()
    private val state = MutableLiveData<ProductsDetailViewState>()
    val detailViewState: LiveData<ProductsDetailViewState> = state

    fun reservationProducts(productId:Int) {
        launch {
            try {
                val responseProducts = reservationProduct.execute(productId)

                if (responseProducts.isSuccessful) {

                    val response = responseProducts.body()

                    if (response != null && response.result.equals("success")) {
                        state.value =
                            ProductsDetailViewState.ReservationProduct(
                                response
                            )
                    }else{
                        reportError()
                    }
                } else {
                    reportError()
                }

            } catch (e: Exception) {
                reportError()
            }
        }
    }

    private fun reportError() {
        state.value = ProductsDetailViewState.ShowErro(true)
    }
}