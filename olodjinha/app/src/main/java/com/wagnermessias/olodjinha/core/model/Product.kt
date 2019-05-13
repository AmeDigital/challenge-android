package com.wagnermessias.olodjinha.core.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Product(
    @SerializedName("id")
    val id: Int,
    @SerializedName("nome")
    val name: String,
    @SerializedName("urlImagem")
    val urlImage: String,
    @SerializedName("descricao")
    val description: String,
    @SerializedName("precoDe")
    val priceFrom: Float,
    @SerializedName("precoPor")
    val pricePer: Float,
    @SerializedName("categoria")
    val category: Category
): Serializable