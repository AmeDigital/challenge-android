package com.luizzabuscka.data.repository

import com.luizzabuscka.commons.model.Banner
import com.luizzabuscka.data.mapper.BannersMapper
import com.luizzabuscka.data.webservice.WebServiceFactory
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class BannersRepository {

    private val service = WebServiceFactory.create()

    fun getBanners(observer: SingleObserver<List<Banner>>) {
        service.getBanners()
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .map { BannersMapper().transform(it) }
            .subscribe({ data ->
                observer.onSuccess(data)
            }, { error ->
                observer.onError(error)
            })
    }

}