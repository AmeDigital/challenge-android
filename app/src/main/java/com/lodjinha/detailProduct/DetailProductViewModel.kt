package com.lodjinha.detailProduct

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lodjinha.R
import com.lodjinha.base.BaseViewModel
import com.lodjinha.model.Product
import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.utils.Resource
import com.lodjinha.utils.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody

class DetailProductViewModel(
    private val reserveProductUseCase: ReserveProductUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    val product: MutableLiveData<Product> = MutableLiveData()

    private val responseMediator = MediatorLiveData<Resource<ResponseBody>>()
    val response: LiveData<Resource<ResponseBody>> get() = responseMediator
    private var responseSource: LiveData<Resource<ResponseBody>> = MutableLiveData()

    fun configProduct(product: Product) {
        this.product.value = product
    }

    fun reserveProduct() {
        reserveProduct(product.value!!.id)
    }

    private fun reserveProduct(productId : Int) = viewModelScope.launch(dispatchers.main) {
        responseMediator.removeSource(responseSource)

        withContext(dispatchers.io) {
            responseSource = reserveProductUseCase(productId)
        }

        responseMediator.addSource(responseSource) {
            responseMediator.value = it
            if (it.status == Resource.Status.ERROR) {
                snackBarErrorMediator.value = Event(R.string.an_error_happened)
            }
        }
    }
}
