package com.br.cinesky.base

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import br.com.amedigital.lojinha.viewmodel.Event

open class BaseViewModel : ViewModel() {
    val eventLoading = MutableLiveData<Event<Boolean>>()
    val eventFailure = MutableLiveData<Event<Throwable>>()

    fun showLoading(value: Boolean) {
        eventLoading.value = Event(value)
    }

    fun showFailure(throwable: Throwable) {
        eventFailure.value = Event(throwable)
    }
}