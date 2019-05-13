package com.wagnermessias.olodjinha.core.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

abstract class BaseViewModel(mainDispatcher: CoroutineDispatcher): ViewModel(), CoroutineScope {
    override val coroutineContext = Dispatchers.Main

    private val job = SupervisorJob()

    protected val scope = CoroutineScope(mainDispatcher + job)


    override fun onCleared() {
        super.onCleared()
        job.cancelChildren()
    }

}