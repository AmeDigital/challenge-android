package com.amedigital.challenge_model.repositories

import com.amedigital.challenge_model.api.ApiResponse
import com.amedigital.challenge_model.api.Resource
import com.amedigital.challenge_model.api.SafeApi

abstract class BaseRepositoryImpl {
    suspend fun <T> safeCallForApiResponse(
        apiCall: suspend () -> ApiResponse<T>
    ): Resource<T> {
        val call = SafeApi.safeCall {
            apiCall()
        }
        return when (call) {
            is Resource.Success -> Resource.Success(call.value.data)
            is Resource.Failure -> call
            else -> Resource.Requesting
        }
    }
}