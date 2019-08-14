package com.luizzabuscka.data.repository

import androidx.paging.PagedList
import androidx.paging.RxPagedListBuilder
import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.commons.model.Product
import com.luizzabuscka.data.mapper.CategoriesMapper
import com.luizzabuscka.data.mapper.ProductsMapper
import com.luizzabuscka.data.webservice.WebServiceFactory
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ProductsRepository {

    private val service = WebServiceFactory.create()

    fun getBestSellers(observer: SingleObserver<List<Product>>) {
        service.getBestSellers()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .map { ProductsMapper().transform(it) }
            .subscribe({ data ->
                observer.onSuccess(data)
            }, { error ->
                observer.onError(error)
            })
    }

    fun reserveProduct(productId: Int, observer: SingleObserver<Boolean>) {
        service.reserveProduct(productId)
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                observer.onSuccess(true)
            }, { error ->
                observer.onError(error)
            })
    }



}