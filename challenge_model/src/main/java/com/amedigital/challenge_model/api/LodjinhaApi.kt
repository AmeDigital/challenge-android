package com.amedigital.challenge_model.api

import com.amedigital.challenge_model.Banner
import retrofit2.http.GET

interface LodjinhaApi {
    @GET("/banner")
    suspend fun getBanner(
    ): ApiResponse<List<Banner>>
}