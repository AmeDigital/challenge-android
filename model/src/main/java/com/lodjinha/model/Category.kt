package com.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Category(
    val id: Int,
    @SerializedName("descricao") val description: String,
    @SerializedName("urlImagem") val urlImage: String
)
