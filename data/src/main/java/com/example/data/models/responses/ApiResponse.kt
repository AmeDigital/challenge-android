package com.example.data.models.responses

import com.google.gson.annotations.SerializedName

data class ApiResponse<T>(
    @SerializedName("data") val results: T
)