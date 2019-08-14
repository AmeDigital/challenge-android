package com.luizzabuscka.data.datasource

import androidx.paging.PageKeyedDataSource
import com.luizzabuscka.commons.model.Product
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.luizzabuscka.commons.model.State
import com.luizzabuscka.data.mapper.ProductsMapper
import com.luizzabuscka.data.webservice.WebService
import com.luizzabuscka.data.webservice.WebServiceFactory
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action
import io.reactivex.schedulers.Schedulers


class ProductDataSource(private val webService: WebService,
                        private val compositeDisposable: CompositeDisposable,
                        private val categoryId: Int) : PageKeyedDataSource<Int, Product>() {

    var state: MutableLiveData<State> = MutableLiveData()
    private var retryCompletable: Completable? = null

    private val pageSize = 10

    private var offset = 0
    private var limit = pageSize

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Product>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            webService.getProducts(offset, limit, categoryId)
                .map { ProductsMapper().transform(it) }
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        offset += pageSize
                        limit += pageSize
                        callback.onResult(response,
                            null,
                            1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadInitial(params, callback) })
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {
        updateState(State.LOADING)
        compositeDisposable.add(
            webService.getProducts(offset, limit, categoryId)
                .map { ProductsMapper().transform(it) }
                .subscribe(
                    { response ->
                        updateState(State.DONE)
                        offset += pageSize
                        limit += pageSize
                        callback.onResult(response,
                            params.key + 1
                        )
                    },
                    {
                        updateState(State.ERROR)
                        setRetry(Action { loadAfter(params, callback) })
                    }
                )
        )
    }

    fun retry() {
        if (retryCompletable != null) {
            compositeDisposable.add(retryCompletable!!
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe())
        }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Product>) {}

    private fun updateState(state: State) {
        this.state.postValue(state)
    }

    private fun setRetry(action: Action?) {
        retryCompletable = if (action == null) null else Completable.fromAction(action)
    }
}


class ProductDataSourceFactory(
    private val compositeDisposable: CompositeDisposable,
    private val categoryId: Int)
    : DataSource.Factory<Int, Product>() {

    private val webService = WebServiceFactory.create()

    val productsDataSourceLiveData = MutableLiveData<ProductDataSource>()

    override fun create(): DataSource<Int, Product> {
        val productDataSource = ProductDataSource(webService, compositeDisposable, categoryId)
        productsDataSourceLiveData.postValue(productDataSource)
        return productDataSource
    }
}