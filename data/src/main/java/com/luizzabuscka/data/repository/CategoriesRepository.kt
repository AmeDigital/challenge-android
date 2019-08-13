package com.luizzabuscka.data.repository

import com.luizzabuscka.commons.model.Category
import com.luizzabuscka.data.mapper.CategoriesMapper
import com.luizzabuscka.data.webservice.WebServiceFactory
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CategoriesRepository {

    private val service = WebServiceFactory.create()

    fun getCategories(observer: SingleObserver<List<Category>>) {
        service.getCategories()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .map { CategoriesMapper().transform(it) }
            .subscribe({ data ->
                observer.onSuccess(data)
            }, { error ->
                observer.onError(error)
            })
    }
}