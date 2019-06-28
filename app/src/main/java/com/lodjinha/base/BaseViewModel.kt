package com.lodjinha.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lodjinha.utils.Event

abstract class BaseViewModel: ViewModel() {

    protected val snackBarErrorMediator = MutableLiveData<Event<Int>>()
    val snackBarError: LiveData<Event<Int>> get() = snackBarErrorMediator
}
