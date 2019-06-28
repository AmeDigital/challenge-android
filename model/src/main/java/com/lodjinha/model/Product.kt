package com.lodjinha.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    @SerializedName("nome") val name: String,
    @SerializedName("descricao") val description: String,
    @SerializedName("urlImagem") val urlImage: String,
    @SerializedName("precoDe") val originalPrice: Double,
    @SerializedName("precoPor") val currentPrice: Double,
    @SerializedName("categoria") val category: Category
)
