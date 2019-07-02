package com.lodjinha.category

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.lodjinha.R
import com.lodjinha.base.BaseViewModel
import com.lodjinha.model.Products
import com.lodjinha.repository.AppDispatchers
import com.lodjinha.repository.utils.Resource
import com.lodjinha.utils.Event
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CategoryViewModel(
    private val getProductsUseCase: GetProductsUseCase,
    private val dispatchers: AppDispatchers
) : BaseViewModel() {

    private val productsMediator = MediatorLiveData<Resource<Products>>()
    val products: LiveData<Resource<Products>> get() = productsMediator
    private var productsSource: LiveData<Resource<Products>> = MutableLiveData()
    private var page = 0
    private var lastCategoryID = 0

    fun configCategory(categoryId: Int) {
        lastCategoryID = categoryId
    }

    fun refreshProducts() {
        page = 0
        getProducts(page)
    }

    fun getProducts(page : Int = 0) = viewModelScope.launch(dispatchers.main) {
        productsMediator.removeSource(productsSource)

        withContext(dispatchers.io) {
            productsSource = getProductsUseCase(page = page, categoryId = lastCategoryID)
        }

        productsMediator.addSource(productsSource) {
            productsMediator.value = it
            if (it.status == Resource.Status.ERROR) {
                snackBarErrorMediator.value = Event(R.string.an_error_happened)
            }
        }
    }
}
