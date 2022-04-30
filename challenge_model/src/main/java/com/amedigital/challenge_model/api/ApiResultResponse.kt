package com.amedigital.challenge_model.api

/*
    Classes que identificam o primeiro elemento do json
*/

data class ApiDataResponse<T>(val data: T, val offset: Int, val total: Int)
data class ApiResultResponse(val result: String)