package com.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val nome: String,
    @SerializedName("descricao") val description: String,
    val urlImagem: String,
    @SerializedName("precoDe") val originalPrice: Float,
    @SerializedName("precoPor") val currentPrice: Float,
    @SerializedName("categoria") val Category: Category
)
