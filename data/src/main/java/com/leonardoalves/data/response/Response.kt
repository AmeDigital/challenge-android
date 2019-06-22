package com.leonardoalves.data.response

import com.google.gson.annotations.SerializedName

class Response<T> {
    @SerializedName("data") var data: List<T>? = null
}