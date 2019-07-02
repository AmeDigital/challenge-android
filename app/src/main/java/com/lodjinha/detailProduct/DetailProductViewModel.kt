package com.lodjinha.detailProduct

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lodjinha.base.BaseViewModel
import com.lodjinha.model.Product
import com.lodjinha.repository.AppDispatchers
import kotlinx.coroutines.launch

class DetailProductViewModel(
    private val productUseCase: ProductUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    val product: MutableLiveData<Product> = MutableLiveData()

    fun configProduct(product: Product) {
        this.product.value = product
    }

    fun reserveProduct() {
        product
    }

    fun getProducts(page : Int = 0) = viewModelScope.launch(dispatchers.main) {
//        productMediator.removeSource(productsSource)
//
//        withContext(dispatchers.io) {
//            productsSource = productUseCase(page = page, categoryId = lastCategoryID)
//        }
//
//        productMediator.addSource(productsSource) {
//            productMediator.value = it
//            if (it.status == Resource.Status.ERROR) {
//                snackBarErrorMediator.value = Event(R.string.an_error_happened)
//            }
//        }
    }
}
