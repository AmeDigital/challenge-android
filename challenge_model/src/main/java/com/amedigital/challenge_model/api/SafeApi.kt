package com.amedigital.challenge_model.api

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SafeApi {
    suspend fun <T> safeCall(
        apiCall: suspend () -> T
    ): Resource<T> {
        return withContext(Dispatchers.IO) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                Resource.Failure(throwable)
            }
        }
    }
}