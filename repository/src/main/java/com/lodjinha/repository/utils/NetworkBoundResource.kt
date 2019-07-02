package com.lodjinha.repository.utils

import android.util.Log
import androidx.annotation.MainThread
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.coroutineContext

abstract class NetworkBoundResource<ResultType, RequestType> {

    private val result = MutableLiveData<Resource<ResultType>>()
    private val supervisorJob = SupervisorJob()

    suspend fun build(): NetworkBoundResource<ResultType, RequestType> {
        withContext(Dispatchers.Main) {
            result.value = Resource.loading(null)
        }

        CoroutineScope(coroutineContext).launch(supervisorJob) {
            if (shouldFetch()) {
                try {
                    fetchFromNetwork()
                } catch (e: Exception) {
                    Log.e("NetworkBoundResource", "An error happened: $e")
                }
            } else {
                Log.d(NetworkBoundResource::class.java.name, "Return data from local database")
            }
        }
        return this
    }

    fun asLiveData() = result as LiveData<Resource<ResultType>>

    private suspend fun fetchFromNetwork() {
        Log.d(NetworkBoundResource::class.java.name, "Fetch data from network")
        val apiResponse = createCallAsync().await()
        Log.e(NetworkBoundResource::class.java.name, "Data fetched from network")
        val responseResultType = processResponse(apiResponse)
        setValue(Resource.success(responseResultType))
    }

    @MainThread
    private fun setValue(newValue: Resource<ResultType>) {
        Log.d(NetworkBoundResource::class.java.name, "Resource: $newValue")
        if (result.value != newValue) {
            result.postValue(newValue)
        }
    }

    @WorkerThread
    protected abstract fun processResponse(response: RequestType): ResultType

    @MainThread
    protected abstract fun shouldFetch(): Boolean

    @MainThread
    protected abstract fun createCallAsync(): Deferred<RequestType>
}
