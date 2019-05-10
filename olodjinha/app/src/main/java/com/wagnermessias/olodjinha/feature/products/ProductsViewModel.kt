package com.wagnermessias.olodjinha.feature.products

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.ProductInteractor
import com.wagnermessias.olodjinha.core.model.Product
import kotlinx.coroutines.launch

class ProductsViewModel(
    private val productInteractor: ProductInteractor

) : BaseViewModel() {
    private val lastProducts: ArrayList<Product> = ArrayList()
    private val state = MutableLiveData<ProductsViewState>()
    val viewState: LiveData<ProductsViewState> = state
//    private val lastProducts: ArrayList<List<Product>>()

    fun loadProductsByCategory(categoryId:Int,offset: Int) {
        launch {
            try {
                val responseProducts = productInteractor.getProductsByCategory(categoryId,offset)

                if (responseProducts.isSuccessful) {

                    val newProducts = responseProducts.body()?.list

                    if (newProducts != null) {
                        lastProducts.addAll(newProducts)
                    }

                    state.value = ProductsViewState.ProductsList(lastProducts)
                } else {
                    reportError()
                }

            } catch (e: Exception) {
                reportError()
            }
        }
    }

    fun reservationProducts(productId:Int) {
        launch {
            try {
                val responseProducts = productInteractor.reservationProduct(productId)

                if (responseProducts.isSuccessful) {

                    val response = responseProducts.body()

                    if (response != null && response.result.equals("success")) {
                        state.value = ProductsViewState.ReservationProduct(response)
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
        state.value = ProductsViewState.ShowErro(true)
    }
}