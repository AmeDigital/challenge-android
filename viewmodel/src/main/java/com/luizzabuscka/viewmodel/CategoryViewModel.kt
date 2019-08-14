package com.luizzabuscka.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.commons.model.State
import com.luizzabuscka.data.datasource.ProductDataSource
import com.luizzabuscka.data.datasource.ProductDataSourceFactory
import io.reactivex.disposables.CompositeDisposable

class CategoryViewModel(categoryId: Int) : ViewModel() {

    var products: LiveData<PagedList<Product>>
    private var productDataSourceFactory: ProductDataSourceFactory

    private val compositeDisposable = CompositeDisposable()
    private val pageSize = 5

    init {
        productDataSourceFactory = ProductDataSourceFactory(compositeDisposable, categoryId)
        val config = PagedList.Config.Builder()
            .setPageSize(pageSize)
            .setInitialLoadSizeHint(pageSize * 2)
            .setEnablePlaceholders(false)
            .build()
        products = LivePagedListBuilder(productDataSourceFactory, config).build()
    }

    fun retry() {
        productDataSourceFactory.productsDataSourceLiveData.value?.retry()
    }

    fun getState(): LiveData<State> = Transformations.switchMap<ProductDataSource,
            State>(productDataSourceFactory.productsDataSourceLiveData, ProductDataSource::state)

    fun listIsEmpty(): Boolean {
        return products.value?.isEmpty() ?: true
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }
}