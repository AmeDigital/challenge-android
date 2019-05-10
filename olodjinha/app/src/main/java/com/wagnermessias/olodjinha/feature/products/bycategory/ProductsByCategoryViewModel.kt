package com.wagnermessias.olodjinha.feature.products.bycategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.LoadProductByCategoryInteractor
import com.wagnermessias.olodjinha.core.model.Product
import kotlinx.coroutines.launch

class ProductsByCategoryViewModel(
    private val loadProductByCategoryInteractor: LoadProductByCategoryInteractor

) : BaseViewModel() {
    private val lastProducts: ArrayList<Product> = ArrayList()
    private val state = MutableLiveData<ProductsByCategoryViewState>()
    val byCategoryViewState: LiveData<ProductsByCategoryViewState> = state

    fun loadProductsByCategory(categoryId:Int,offset: Int) {
        launch {
            try {
                val responseProducts = loadProductByCategoryInteractor.execute(categoryId,offset)

                if (responseProducts.isSuccessful) {

                    val newProducts = responseProducts.body()?.list

                    if (newProducts != null) {
                        lastProducts.addAll(newProducts)
                    }
                    state.value =
                        ProductsByCategoryViewState.ProductsByCategoryList(
                            lastProducts
                        )
                } else {
                    reportError()
                }

            } catch (e: Exception) {
                reportError()
            }
        }
    }

    private fun reportError() {
        state.value = ProductsByCategoryViewState.ShowErro(true)
    }
}