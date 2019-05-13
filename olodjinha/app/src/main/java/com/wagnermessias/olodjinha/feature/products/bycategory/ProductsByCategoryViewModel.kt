package com.wagnermessias.olodjinha.feature.products.bycategory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.wagnermessias.olodjinha.core.base.BaseViewModel
import com.wagnermessias.olodjinha.core.interactor.LoadProductByCategoryInteractor
import com.wagnermessias.olodjinha.core.model.Product
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.IOException

class ProductsByCategoryViewModel(
    private val loadProductByCategoryInteractor: LoadProductByCategoryInteractor,
    mainDispatcher: CoroutineDispatcher = Dispatchers.Main
) : BaseViewModel(mainDispatcher) {
    private val lastProducts: ArrayList<Product> = ArrayList()
    private val state = MutableLiveData<ProductsByCategoryViewState>()
    val byCategoryViewState: LiveData<ProductsByCategoryViewState> = state

    fun loadProductsByCategory(categoryId: Int, offset: Int) {
        scope.launch {
            try {
                val responseProducts = loadProductByCategoryInteractor.execute(categoryId, offset)

                if (responseProducts.isSuccessful) {

                    val newProducts = responseProducts.body()?.list

                    if (newProducts != null && newProducts.size > 0) {
                        lastProducts.addAll(newProducts)
                    }

                    if (lastProducts.size > 0) {
                        state.value = ProductsByCategoryViewState.ProductsByCategoryList(lastProducts)
                    } else {
                        state.value = ProductsByCategoryViewState.EmptyList
                    }
                } else {
                    state.value = ProductsByCategoryViewState.ServerError
                }
            } catch (error: IOException) {
                state.value = ProductsByCategoryViewState.NetworkError
            }
        }
    }
}