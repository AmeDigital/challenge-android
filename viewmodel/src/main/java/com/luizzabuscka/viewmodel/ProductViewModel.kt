package com.luizzabuscka.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.luizzabuscka.data.repository.ProductsRepository
import io.reactivex.SingleObserver
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

class ProductViewModel : ViewModel() {

    private val productsRepository = ProductsRepository()
    private var reserve = MutableLiveData<Boolean>()

    fun reserveProduct(productId: Int): LiveData<Boolean> {
        val compositeDisposable = CompositeDisposable()
        productsRepository.reserveProduct(productId, object : SingleObserver<Boolean> {
            override fun onSuccess(success: Boolean) {
                reserve.value = success
                compositeDisposable.clear()
            }

            override fun onSubscribe(d: Disposable) {
                compositeDisposable.add(d)
            }

            override fun onError(e: Throwable) {
                onSuccess(false)
                compositeDisposable.clear()
            }

        })
        return reserve
    }

}