package com.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Banner(
    val id: Int,
    val linkUrl: String,
    @SerializedName("urlImagem") val urlImage: String
)
