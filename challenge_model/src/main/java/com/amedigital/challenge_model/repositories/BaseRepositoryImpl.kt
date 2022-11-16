package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.api.ApiDataResponse
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.api.SafeApi

abstract class BaseRepositoryImpl {
    suspend fun <T> safeCallForApiDataResponse(
        apiDataCall: suspend () -> ApiDataResponse<T>
    ): Resource<T> {
        val call = SafeApi.safeCall {
            apiDataCall()
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value.data)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }

    suspend fun <T> safeCallForApiDirectResponse(
        apiDataCall: suspend () -> T
    ): Resource<T> {
        val call = SafeApi.safeCall {
            apiDataCall()
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }
}